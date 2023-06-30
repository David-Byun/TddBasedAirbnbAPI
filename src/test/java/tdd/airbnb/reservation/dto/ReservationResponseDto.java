package tdd.airbnb.reservation.dto;

import lombok.*;
import tdd.airbnb.room.Room;
import tdd.airbnb.room.Status;
import tdd.airbnb.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReservationResponseDto {
    private String reservationCode;
    private int totalPrice;
    private int totalGuest;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
//    private boolean isReviewCreated;
    private Room room;
    private User user;
}
