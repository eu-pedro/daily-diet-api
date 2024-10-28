package daily_diet.demo.application.services.exceptions;

import daily_diet.demo.application.services.erros.MealNotFoundError;
import daily_diet.demo.application.services.erros.UserAlreadyExistsError;
import daily_diet.demo.application.services.erros.UserNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsError.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsError ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", ex.getReason()));
    }

    @ExceptionHandler(UserNotFoundError.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundError ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getReason()));
    }

    @ExceptionHandler(MealNotFoundError.class)
    public ResponseEntity<Map<String, String>> handleMealNotFoundException(MealNotFoundError ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getReason()));
    }
}
