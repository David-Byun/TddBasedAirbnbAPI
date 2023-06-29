package tdd.airbnb.test.room;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tdd.airbnb.room.RoomMapper;
import tdd.airbnb.room.dto.RoomDetailResponseDto;
import tdd.airbnb.room.dto.RoomListResponseDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class RoomMapperTest {

    @Autowired
    private RoomMapper roomMapper;

    private final Long roomId = 1L;

    @Test
    void RoomMapper가NULL이아님() {
        assertThat(roomMapper).isNotNull();
    }

    /**
     * 데이터 3개
     */
    @Test
    void 룸전체조회테스트() {

        //given
        List<RoomListResponseDto> allRooms = roomMapper.findAllRooms();

        //then
        assertThat(allRooms.size()).isEqualTo(3);
    }

    @Test
    void Room세부정보조회테스트() {

        //when
        Optional<RoomDetailResponseDto> roomByRoomId = roomMapper.findRoomByRoomId(roomId);

        //then
        assertThat(roomByRoomId.get().getPrice()).isEqualTo(3000000);

    }


}






















