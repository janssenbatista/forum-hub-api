package blog.jdev.forum.hub.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "answer")
@Table(name = "tb_answers")
@Data
@EqualsAndHashCode(of = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String message;
    @Column(columnDefinition = "TINYINT")
    Integer solution;
    @CreationTimestamp
    LocalDateTime createdAt;
}
