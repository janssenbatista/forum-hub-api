package blog.jdev.forum.hub.api.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties
public record UpdateTopicRequestDTO(
        @NotBlank(message = "title cannot be blank")
        String title,
        @NotBlank(message = "message cannot be blank")
        String message,
        @NotBlank
        String author,
        @Min(1)
        @Max(Integer.MAX_VALUE)
        Integer courseId
) {
}
