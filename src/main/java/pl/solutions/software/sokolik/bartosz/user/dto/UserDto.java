package pl.solutions.software.sokolik.bartosz.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@Value
@Builder
@ApiModel(value = "User dto", description = "Dto with user details")
public class UserDto {

    @ApiModelProperty(value = "User username")
    private String username;

    @ApiModelProperty(value = "User password")
    private String password;

    @ApiModelProperty(value = "User email")
    private String email;

    @ApiModelProperty(value = "User role", allowableValues = "aa, aa")
    private String role;
}
