package tdd.airbnb.test.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdd.airbnb.exception.ReservationException;
import tdd.airbnb.reservation.ReservationMapper;
import tdd.airbnb.reservation.dto.ReservationResponseDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationMapper reservationMapper;
    public ReservationResponseDto findReservationByIdAndUserId(long userId, long reservationId) {
        ReservationResponseDto reservation = reservationMapper.findReservationByIdAndUserId(userId, reservationId).orElseThrow(new ReservationException("예약이 존재하지 않습니다."));

        return reservation;
    }
}
