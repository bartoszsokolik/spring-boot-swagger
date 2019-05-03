package pl.solutions.software.sokolik.bartosz.movie;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.solutions.software.sokolik.bartosz.movie.domain.MovieFacade;
import pl.solutions.software.sokolik.bartosz.movie.dto.MovieDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
@Api(tags = "Movie Controller")
class MovieController {

    private final MovieFacade movieFacade;

    @ApiOperation(value = "Find all movies",
            notes = "Finds all movies",
            response = MovieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = MovieDto.class),
            @ApiResponse(code = 500, message = "In case of any error")
    })
    @GetMapping
    List<MovieDto> findAll() {
        return movieFacade.findAll();
    }

    @ApiOperation(value = "Find movie by id",
            notes = "Finds movie by id",
            response = MovieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "In case of any error")
    })
    @GetMapping("/{id}")
    ResponseEntity<MovieDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(movieFacade.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Find movie by title",
            notes = "Finds movie by title",
            response = MovieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "In case of any error")
    })
    @GetMapping("/title/{title}")
    ResponseEntity<MovieDto> findByTitle(@PathVariable String title) {
        return new ResponseEntity<>(movieFacade.findByTitle(title), HttpStatus.OK);
    }

    @ApiOperation(value = "Add movie",
            notes = "Creates movie based on passed MovieDto"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "In case of any error")
    })
    @PostMapping
    ResponseEntity<Void> addMovie(@RequestBody MovieDto dto) {
        Long movieId = movieFacade.createMovie(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(movieId).toUri();

        return ResponseEntity.created(location).build();
    }

}
