package blog.jdev.forum.hub.api.services;

import blog.jdev.forum.hub.api.controllers.dtos.AuthRequestDTO;
import blog.jdev.forum.hub.api.controllers.dtos.AuthResponseDTO;
import blog.jdev.forum.hub.api.models.User;
import blog.jdev.forum.hub.api.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public AuthResponseDTO login(AuthRequestDTO dto) {
        User user = userRepository
                .findByEmail(dto.email()).orElseThrow(() -> new UsernameNotFoundException("invalid email or password"));
        boolean isPasswordValid = passwordEncoder.matches(dto.password(), user.getPassword());
        if (!user.getEmail().equalsIgnoreCase(dto.email()) || !isPasswordValid) {
            throw new UsernameNotFoundException("invalid email or password");
        }
        String token = tokenService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}
