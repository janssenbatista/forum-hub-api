package blog.jdev.forum.hub.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {
    private final int httpStatusCode;

    public BadRequestException(String message) {
        super(message);
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
    }
}
