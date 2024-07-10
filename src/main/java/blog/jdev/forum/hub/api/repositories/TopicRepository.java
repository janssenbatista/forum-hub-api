package blog.jdev.forum.hub.api.repositories;

import blog.jdev.forum.hub.api.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

    Optional<Topic> findByTitleAndMessage(String title, String message);
}
