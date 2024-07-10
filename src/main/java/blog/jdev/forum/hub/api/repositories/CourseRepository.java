package blog.jdev.forum.hub.api.repositories;

import blog.jdev.forum.hub.api.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
