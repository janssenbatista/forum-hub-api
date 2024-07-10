package blog.jdev.forum.hub.api.exceptions.handlers;

import blog.jdev.forum.hub.api.exceptions.BadRequestException;
import blog.jdev.forum.hub.api.exceptions.ConflictException;
import blog.jdev.forum.hub.api.exceptions.ForbiddenException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handle(RuntimeException ex) {
        if (ex instanceof ConflictException conflictException) {
            return ResponseEntity.status(conflictException.getHttpStatusCode()).body(conflictException.getMessage());
        }
        if (ex instanceof BadRequestException badRequestException) {
            return ResponseEntity.status(badRequestException.getHttpStatusCode()).body(badRequestException.getMessage());
        }
        if (ex instanceof UsernameNotFoundException usernameNotFoundException) {
            return ResponseEntity.badRequest().body(usernameNotFoundException.getMessage());
        }
        if (ex instanceof ValidationException validationException) {
            return ResponseEntity.badRequest().body(validationException.getMessage());
        }
        if (ex instanceof ForbiddenException forbiddenException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(forbiddenException.getMessage());
        }
        return ResponseEntity.internalServerError().build();
    }
}
