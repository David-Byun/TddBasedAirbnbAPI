package tdd.airbnb;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseTimeEntity {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
