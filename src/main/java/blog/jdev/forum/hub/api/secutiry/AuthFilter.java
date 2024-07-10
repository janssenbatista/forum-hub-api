package blog.jdev.forum.hub.api.secutiry;

import blog.jdev.forum.hub.api.services.TokenService;
import blog.jdev.forum.hub.api.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthFilter(TokenService tokenService, UserDetailsServiceImpl userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("authorization") != null) {
            String token = getToken(request);
            if (token != null) {
                String username = getUsername(token);
                UserDetails user = userDetailsService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        return request.getHeader("authorization").split(" ")[1];
    }

    private String getUsername(String token) {
        return tokenService.getDecodedJWT(token).getSubject();
    }
}
