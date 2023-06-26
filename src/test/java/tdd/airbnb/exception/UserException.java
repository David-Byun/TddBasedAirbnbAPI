package tdd.airbnb.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final String status;

    public UserException(String status) {
        this.status = status;
    }
}
