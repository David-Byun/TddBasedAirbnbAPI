package tdd.airbnb.test.reservation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.airbnb.reservation.ReservationMapper;
import tdd.airbnb.reservation.dto.ReservationResponseDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@Slf4j
class ReservationMapperTest {

    @Autowired
    private ReservationMapper reservationMapper;

    private final long userId = 1L;
    private final long reservationId = 1L;

    @Test
    @DisplayName("Reservation 조회")
    void findReservation() {

        //given
        Optional<ReservationResponseDto> reservation = reservationMapper.findReservationByIdAndUserId(userId, reservationId);

        log.info("=========== reservation : {} =============", reservation.toString());

        //then
        assertThat(reservation.get().getTotalGuest()).isEqualTo(1);
    }

    @Test
    @DisplayName("Reservation 데이터 없는 경우 테스트")
    void findReservationException() {

        //given
        Optional<ReservationResponseDto> reservation = reservationMapper.findReservationByIdAndUserId(3L, 3L);

        log.info("=========== reservation : {} =============", reservation.toString());

        //then
        assertThat(reservation.isEmpty()).isEqualTo(true);

        //예외를 throw 하기로 작성된 메서드의 정상 작동을 확인하기 위해 예외를 throw 하지 않고 실행되는 void 메서드를 통해 테스트 assertThatCode 이용
        assertThatCode(() -> reservationMapper.findReservationByIdAndUserId(3L, 3L))
                .doesNotThrowAnyException();
    }
}
