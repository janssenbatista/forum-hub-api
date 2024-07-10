package blog.jdev.forum.hub.api.controllers;

import blog.jdev.forum.hub.api.controllers.dtos.TopicRequestDTO;
import blog.jdev.forum.hub.api.controllers.dtos.TopicResponseDTO;
import blog.jdev.forum.hub.api.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> create(@RequestBody @Valid TopicRequestDTO dto, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.create(dto, authentication));
    }
}
