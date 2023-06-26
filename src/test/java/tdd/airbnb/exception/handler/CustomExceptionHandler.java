package tdd.airbnb.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tdd.airbnb.exception.UserException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({UserException.class})
    public String handleUserException(UserException e) {
        return e.getStatus();
    }
}
