package blog.jdev.forum.hub.api.services;

import blog.jdev.forum.hub.api.exceptions.ForbiddenException;
import blog.jdev.forum.hub.api.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    private final String ISSUER = "forum-hub-api";

    public String generateToken(User user) {
        final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Instant now = Instant.now();
        final int ONE_HOUR_IN_MILLI = 60 * 60 * 1_000;
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(ONE_HOUR_IN_MILLI))
                .withSubject(user.getEmail())
                .sign(algorithm);
    }

    public DecodedJWT getDecodedJWT(String token) {
        final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            return verifier.verify(token);
        } catch (TokenExpiredException ex) {
            throw new ForbiddenException(ex.getMessage());
        }
    }
}
