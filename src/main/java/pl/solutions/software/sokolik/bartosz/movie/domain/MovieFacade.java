package pl.solutions.software.sokolik.bartosz.movie.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.movie.dto.MovieDto;
import pl.solutions.software.sokolik.bartosz.movie.exception.MovieNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieFacade {

    private final MovieRepository movieRepository;
    private final MovieAssembler movieAssembler;

    public List<MovieDto> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieAssembler::fromDomain)
                .collect(Collectors.toList());
    }

    public MovieDto findByTitle(String title) {
        return movieRepository.findByTitle(title)
                .map(movieAssembler::fromDomain)
                .orElseThrow(() -> new MovieNotFoundException(String.format("Movie with title %s not found", title)));
    }

    public MovieDto findById(Long id) {
        return movieRepository.findById(id)
                .map(movieAssembler::fromDomain)
                .orElseThrow(() -> new MovieNotFoundException(String.format("Movie with id %s not found", id)));
    }

    public Long createMovie(MovieDto dto) {
        Movie movie = movieAssembler.fromDto(dto);
        return movieRepository.save(movie).getId();
    }

}
