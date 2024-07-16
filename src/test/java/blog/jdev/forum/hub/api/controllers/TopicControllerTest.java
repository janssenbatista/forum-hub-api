package blog.jdev.forum.hub.api.controllers;

import blog.jdev.forum.hub.api.controllers.dtos.TopicDetailResponseDTO;
import blog.jdev.forum.hub.api.controllers.dtos.TopicRequestDTO;
import blog.jdev.forum.hub.api.controllers.dtos.TopicResponseDTO;
import blog.jdev.forum.hub.api.controllers.dtos.UpdateTopicRequestDTO;
import blog.jdev.forum.hub.api.models.TopicStatus;
import blog.jdev.forum.hub.api.services.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TopicControllerTest {

    @InjectMocks
    private TopicController topicController;

    @Mock
    private TopicService topicService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        TopicRequestDTO requestDTO = new TopicRequestDTO("title", "message", 1);
        TopicResponseDTO responseDTO = new TopicResponseDTO(UUID.randomUUID(), "title", "message", LocalDateTime.now());
        when(topicService.create(any(TopicRequestDTO.class), any(Authentication.class))).thenReturn(responseDTO);
        ResponseEntity<TopicResponseDTO> response = topicController.create(requestDTO, authentication);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testGetAllTopics() {
        Page<TopicDetailResponseDTO> topicsPage = new PageImpl<>(List.of(new TopicDetailResponseDTO(UUID.randomUUID(), "title", "message", LocalDateTime.now(), TopicStatus.NOT_ANSWERED, "author", "couse")));
        when(topicService.getAllTopics(any(PageRequest.class), anyString())).thenReturn(topicsPage);
        ResponseEntity<Page<TopicDetailResponseDTO>> response = topicController.getAllTopics(0, 10, "");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(topicsPage, response.getBody());
    }

    @Test
    void testGetTopicById() {
        UUID topicId = UUID.randomUUID();
        TopicDetailResponseDTO responseDTO = new TopicDetailResponseDTO(topicId, "title", "message", LocalDateTime.now(), TopicStatus.NOT_ANSWERED, "author", "couse");
        when(topicService.getTopicById(topicId)).thenReturn(responseDTO);
        ResponseEntity<TopicDetailResponseDTO> response = topicController.getTopicById(topicId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testUpdateTopic() {
        UUID topicId = UUID.randomUUID();
        UpdateTopicRequestDTO requestDTO = new UpdateTopicRequestDTO("newTitle", "newMessage", "user", 1);
        TopicResponseDTO responseDTO = new TopicResponseDTO(topicId, "newTitle", "newMessage", LocalDateTime.now());
        when(topicService.updateTopic(any(UpdateTopicRequestDTO.class), any(UUID.class), any(Authentication.class))).thenReturn(responseDTO);
        ResponseEntity<TopicResponseDTO> response = topicController.updateTopic(requestDTO, topicId, authentication);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testDeleteTopic() {
        UUID topicId = UUID.randomUUID();
        ResponseEntity<Void> response = topicController.deleteTopic(topicId, authentication);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
