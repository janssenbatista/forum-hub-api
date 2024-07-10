package blog.jdev.forum.hub.api.controllers.dtos;

import blog.jdev.forum.hub.api.models.Topic;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record TopicResponseDTO(UUID id, String title, String message,
                               @JsonFormat
                                       (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
                               LocalDateTime createdAt) {
    public TopicResponseDTO toResponseDTO(Topic topic) {
        return new TopicResponseDTO(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreatedAt());
    }
}
