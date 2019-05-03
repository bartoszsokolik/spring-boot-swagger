package pl.solutions.software.sokolik.bartosz.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import pl.solutions.software.sokolik.bartosz.user.dto.UserDto;

import java.util.List;

@Api(tags = "User controller")
interface UserControllerSwagger {

    @ApiOperation(value = "Find all users",
            notes = "Finds all users",
            response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "In case of any error")
    })
    List<UserDto> findAll();

    @ApiOperation(value = "Find user by username",
            notes = "Finds user by username",
            response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "In case of any error")
    })
    ResponseEntity<UserDto> findByUsername(String username);

    @ApiOperation(value = "Add user",
            notes = "Creates new user based on passed dto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 500, message = "In case of any error")
    })
    ResponseEntity<Void> addUser(UserDto dto);
}
