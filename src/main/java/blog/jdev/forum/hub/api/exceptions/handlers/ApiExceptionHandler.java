package blog.jdev.forum.hub.api.exceptions.handlers;

import blog.jdev.forum.hub.api.exceptions.BadRequestException;
import blog.jdev.forum.hub.api.exceptions.ConflictException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException ex) {
        if (ex instanceof ConflictException conflictException) {
            return ResponseEntity.status(conflictException.getHttpStatusCode()).body(ex.getMessage());
        }
        if (ex instanceof BadRequestException badRequestException) {
            return ResponseEntity.status(badRequestException.getHttpStatusCode()).body(ex.getMessage());
        }
        if (ex instanceof UsernameNotFoundException usernameNotFoundException) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}
