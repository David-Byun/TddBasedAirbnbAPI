package tdd.airbnb.test.reservation;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import tdd.airbnb.reservation.ReservationMapper;
import tdd.airbnb.reservation.dto.ReservationResponseDto;
import tdd.airbnb.room.Room;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Slf4j
class ReservationServiceTest {

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("예약 조회 성공 테스트")
    void reservationTest() {

        //given
        when(reservationMapper.findReservationByIdAndUserId(1L, 1L)).thenReturn(null);

        //when
        ReservationResponseDto reservation = reservationService.findReservationByIdAndUserId(1L, 1L);

        //then
        Assertions.assertThat(reservation.getRoom().getRoomName()).isEqualTo("room_name1");
    }
}
