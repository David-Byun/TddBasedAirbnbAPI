package tdd.airbnb.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tdd.airbnb.room.Room;
import tdd.airbnb.room.Status;
import tdd.airbnb.user.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    private long id;
    private String reservationCode;
    private int totalPrice;
    private int totalGuest;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private boolean isReviewCreated;
    private Room room;
    private User user;
//    private Review review;
}
