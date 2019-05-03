package pl.solutions.software.sokolik.bartosz.movie.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@ApiModel(value = "Movie dto", description = "Dto with movie details")
public class MovieDto {

    @ApiModelProperty(value = "Movie title")
    private String title;

}
