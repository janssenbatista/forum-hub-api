package blog.jdev.forum.hub.api.services;

import blog.jdev.forum.hub.api.controllers.dtos.TopicDetailResponseDTO;
import blog.jdev.forum.hub.api.controllers.dtos.TopicRequestDTO;
import blog.jdev.forum.hub.api.controllers.dtos.TopicResponseDTO;
import blog.jdev.forum.hub.api.exceptions.BadRequestException;
import blog.jdev.forum.hub.api.exceptions.ConflictException;
import blog.jdev.forum.hub.api.exceptions.ForbiddenException;
import blog.jdev.forum.hub.api.models.Role;
import blog.jdev.forum.hub.api.models.Topic;
import blog.jdev.forum.hub.api.models.TopicStatus;
import blog.jdev.forum.hub.api.repositories.CourseRepository;
import blog.jdev.forum.hub.api.repositories.TopicRepository;
import blog.jdev.forum.hub.api.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public TopicResponseDTO create(TopicRequestDTO dto, Authentication authentication) {
        var topicExists = topicRepository.findByTitleAndMessage(dto.title(), dto.message());
        if (topicExists.isPresent()) {
            throw new ConflictException("topic already exists");
        }
        var user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new BadRequestException("user not found"));
        // ADMIN USERS CANNOT CREATE A TOPIC
        if (user.getRoles().stream().map(Role::getName).toList().contains("ADMIN")) {
            throw new ForbiddenException("");
        }
        var course = courseRepository.findById(dto.courseId()).orElseThrow(() -> new BadRequestException("course not found"));
        var newTopic = new Topic(UUID.randomUUID(), dto.title(), dto.message(), LocalDateTime.now(), TopicStatus.NOT_ANSWERED, new HashSet<>(), user, course);
        var topic = topicRepository.save(newTopic);
        return topic.toTopicResponseDTO();
    }

    public Page<TopicDetailResponseDTO> getAllTopics(Pageable pageable, String courseName) {
        if (!courseName.isBlank()) {
            return topicRepository.findAllByCourseName(pageable, courseName).map(Topic::toTopicDetailResponseDTO);
        }
        return topicRepository.findAll(pageable).map(Topic::toTopicDetailResponseDTO);
    }

    public TopicDetailResponseDTO getTopicById(UUID id) {
        return topicRepository.findById(id).orElseThrow(() -> new BadRequestException("topic not found")).toTopicDetailResponseDTO();
    }
}
