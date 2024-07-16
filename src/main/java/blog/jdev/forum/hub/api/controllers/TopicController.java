package blog.jdev.forum.hub.api.controllers;

import blog.jdev.forum.hub.api.controllers.dtos.TopicDetailResponseDTO;
import blog.jdev.forum.hub.api.controllers.dtos.TopicRequestDTO;
import blog.jdev.forum.hub.api.controllers.dtos.TopicResponseDTO;
import blog.jdev.forum.hub.api.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<TopicDetailResponseDTO>> getAllTopics(@RequestParam(defaultValue = "0") int pageNumber,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     @RequestParam(required = false, defaultValue = "") String courseName) {
        return ResponseEntity.ok(topicService.getAllTopics(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "createdAt")), courseName));
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicDetailResponseDTO> getTopicById(@PathVariable UUID id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }
}
