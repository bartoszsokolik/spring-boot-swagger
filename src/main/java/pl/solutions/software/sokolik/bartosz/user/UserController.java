package pl.solutions.software.sokolik.bartosz.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.solutions.software.sokolik.bartosz.user.domain.UserFacade;
import pl.solutions.software.sokolik.bartosz.user.dto.UserDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
class UserController implements UserControllerSwagger {

    private final UserFacade userFacade;

    @Override
    @GetMapping
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @Override
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userFacade.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userFacade.findById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDto dto) {
        Long userId = userFacade.addUser(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(userId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
