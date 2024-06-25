package soft.progress.assignment.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleApiException(CustomException ex) {
        logger.severe("Handled CustomException: " + ex);
        ErrorMessage errorMessage = new ErrorMessage(ex.getStatus().value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, ex.getStatus());
    }
}
