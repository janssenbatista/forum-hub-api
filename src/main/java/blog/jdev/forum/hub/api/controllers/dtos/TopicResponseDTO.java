package blog.jdev.forum.hub.api.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record TopicResponseDTO(UUID id, String title, String message,
                               @JsonFormat
                                       (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
                               LocalDateTime createdAt) {
}
