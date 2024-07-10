package blog.jdev.forum.hub.api.models;

import blog.jdev.forum.hub.api.controllers.dtos.TopicResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "topic")
@Table(name = "tb_topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(unique = true, nullable = false, length = 70)
    private String title;
    @Column(nullable = false)
    private String message;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TopicStatus status;
    @OneToMany(mappedBy = "topic")
    private Set<Answer> answers = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public TopicResponseDTO toTopicResponseDTO() {
        return new TopicResponseDTO(this.getId(), this.getTitle(), this.getMessage(), this.getCreatedAt());
    }
}
