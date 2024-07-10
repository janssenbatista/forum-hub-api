package blog.jdev.forum.hub.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "profile")
@Table(name = "tb_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "TINYINT NOT NULL PRIMARY KEY AUTO_INCREMENT")
    private Integer id;
    @Column(nullable = false, length = 5, unique = true)
    private String name;
}
