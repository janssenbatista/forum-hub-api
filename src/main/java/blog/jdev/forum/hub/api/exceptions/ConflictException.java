package blog.jdev.forum.hub.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ConflictException extends RuntimeException {

    private final int httpStatusCode;

    public ConflictException(String message) {
        super(message);
        this.httpStatusCode = HttpStatus.CONFLICT.value();
    }
}
