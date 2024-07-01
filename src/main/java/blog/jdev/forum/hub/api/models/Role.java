package blog.jdev.forum.hub.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "profile")
@Table(name = "tb_roles")
@Data
@EqualsAndHashCode(of = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT")
    Integer id;
    @Column(nullable = false, length = 5, unique = true)
    String name;
    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}
