package blog.jdev.forum.hub.api.controllers.dtos;

import blog.jdev.forum.hub.api.models.TopicStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record TopicListResponseDTO(UUID id, String title, String message,
                                   @JsonFormat
                                           (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
                                   LocalDateTime createdAt, TopicStatus topicStatus, String author, String course) {
}
