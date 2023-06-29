package tdd.airbnb.exception;

import lombok.Getter;

@Getter
public class RoomException extends RuntimeException{
    private final String status;

    public RoomException(String status) {
        this.status = status;
    }
}
