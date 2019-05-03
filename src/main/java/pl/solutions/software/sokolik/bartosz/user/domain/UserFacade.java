package pl.solutions.software.sokolik.bartosz.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.solutions.software.sokolik.bartosz.user.dto.UserDto;
import pl.solutions.software.sokolik.bartosz.user.exception.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userAssembler::fromDomain)
                .collect(Collectors.toList());
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userAssembler::fromDomain)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not with username %s not found", username)));
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userAssembler::fromDomain)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));
    }

    public Long addUser(UserDto dto) {
        User user = userAssembler.fromDto(dto);
        return userRepository.save(user).getId();
    }
}
