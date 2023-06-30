package tdd.airbnb.reservation;

import org.apache.ibatis.annotations.Mapper;
import tdd.airbnb.reservation.dto.ReservationResponseDto;

import java.util.Optional;

/**
 * id
 */
@Mapper
public interface ReservationMapper {

    Optional<ReservationResponseDto> findReservationByIdAndUserId(long userId, long reservationId);
}
