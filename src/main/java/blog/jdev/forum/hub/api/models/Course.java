package blog.jdev.forum.hub.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "course")
@Table(name = "tb_courses")
@Data
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, length = 100)
    private String name;
    @Column(length = 50)
    private String category;
}
