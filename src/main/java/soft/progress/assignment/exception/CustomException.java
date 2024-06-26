package soft.progress.assignment.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public class CustomException extends RuntimeException{
    private final HttpStatus status;

    public CustomException(String message, int status) {
        super(message);
        this.status = HttpStatus.valueOf(status);
        log.error("{} - Status Code: {}", message, status);
    }

}
