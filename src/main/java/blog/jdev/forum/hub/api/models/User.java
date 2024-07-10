package blog.jdev.forum.hub.api.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private final Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_%s".formatted(role.getName()))).toList();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

}
