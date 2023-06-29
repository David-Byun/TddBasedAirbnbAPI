package tdd.airbnb.test.room;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.airbnb.exception.RoomException;
import tdd.airbnb.room.RoomMapper;
import tdd.airbnb.room.RoomService;
import tdd.airbnb.room.Status;
import tdd.airbnb.room.dto.RoomDetailResponseDto;
import tdd.airbnb.room.dto.RoomListResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class RoomServiceTest {

    @Mock
    private RoomMapper roomMapper;

    @InjectMocks
    private RoomService roomService;

    /**
     * 1. 룸 전체조회 테스트시 룸이 없을 때 예외 처리(데이터 3개 가정)
     * 2. 룸 전체조회 테스트
     * 3. 룸 상세조회 테스트시 룸이 없을 때 예외 처리
     * 4. 룸 상세조회 테스트
     * 5. 룸 상태가 Nonactive 인 경우 예외 처리
     */
    @Nested
    @DisplayName("룸 전체조회 테스트")
    class RoomAllTest {
        @Test
        @DisplayName("성공")
        void success() {

            //given
            when(roomMapper.findAllRooms()).thenReturn(Arrays.asList(new RoomListResponseDto(), new RoomListResponseDto(), new RoomListResponseDto()));

            //when
            List<RoomListResponseDto> allRooms = roomService.findAllRooms();

            //then
            assertThat(allRooms.size()).isEqualTo(3);
        }

        @Test
        @DisplayName("룸 존재하지 않을 경우 예외 처리")
        void fail() {

            //given
            when(roomMapper.findAllRooms()).thenReturn(null);

            //when
            RoomException roomException = assertThrows(RoomException.class, () -> {
                roomService.findAllRooms();
            });

            //then
            assertThat(roomException.getStatus()).isEqualTo("방이 존재하지 않아요");

        }
    }


    @Nested
    @DisplayName("룸 전체조회 테스트")
    class RoomDetailTest {
        @Test
        @DisplayName("성공")
        void success() {

            //given
            when(roomMapper.findRoomByRoomId(1L)).thenReturn(Optional.ofNullable(dto1L));

            //when
            RoomDetailResponseDto result = roomService.findRoomByRoomId(1L);

            //then
            assertThat(result.getRoomName()).isEqualTo("room_name1");

        }
        @Test
        @DisplayName("방이 비활성화시 예외처리")
        void nonactiveException() {

            //given
            when(roomMapper.findRoomByRoomId(2L)).thenReturn(Optional.ofNullable(dto2L));

            //when
            RoomException roomException = assertThrows(RoomException.class, () -> {
                roomService.findRoomByRoomId(2L);
            });

            //then
            assertThat(roomException.getStatus()).isEqualTo("방이 비활성화상태에요");
        }

        @Test
        @DisplayName("방이 미존재시 예외 처리")
        void 룸상세조회시없을때예외처리() {

            //given
            when(roomMapper.findRoomByRoomId(10L)).thenReturn(Optional.empty());

            //when
            RoomException roomException = assertThrows(RoomException.class, () ->
                    roomService.findRoomByRoomId(10L));

            //then
            assertThat(roomException.getStatus()).isEqualTo("방이 존재하지 않아요");

        }
    }






    private RoomDetailResponseDto dto1L = RoomDetailResponseDto.builder()
            .roomId(1L)
            .hostName("testname")
            .metropolitan("서울특별시")
            .city("서울시")
            .town("광진구")
            .roomName("room_name1")
            .latitude(126.977969F)
            .longitude(37.566535F)
            .price(3000000)
            .maxGuest(10)
            .status(Status.ACTIVE)
            .roomDescription("test description")
            .checkinTime("2023-06-12 00:00:00")
            .checkoutTime("2024-06-15 00:00:00")
            .build();

    private RoomDetailResponseDto dto2L = RoomDetailResponseDto.builder()
            .roomId(2L)
            .hostName("kakao_David")
            .metropolitan("부산광역시")
            .city("북구")
            .town("진하구")
            .roomName("room_name2")
            .latitude(127.977969F)
            .longitude(36.566535F)
            .price(1200000)
            .maxGuest(10)
            .status(Status.NONACTIVE)
            .roomDescription("test description")
            .checkinTime("2023-06-12 00:00:00")
            .checkoutTime("2024-06-15 00:00:00")
            .build();
}






























