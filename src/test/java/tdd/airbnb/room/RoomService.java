package tdd.airbnb.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tdd.airbnb.exception.RoomException;
import tdd.airbnb.room.RoomMapper;
import tdd.airbnb.room.dto.RoomDetailResponseDto;
import tdd.airbnb.room.dto.RoomListResponseDto;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomService {

    private final RoomMapper roomMapper;
    public List<RoomListResponseDto> findAllRooms() {

        List<RoomListResponseDto> allRooms = roomMapper.findAllRooms();
        if (allRooms == null) {
            throw new RoomException("방이 존재하지 않아요");
        }
        return allRooms;
    }

    public RoomDetailResponseDto findRoomByRoomId(long roomId) {

        //방이 존재하지 않을 경우
        RoomDetailResponseDto foundedRoom = roomMapper.findRoomByRoomId(roomId).orElseThrow(() -> new RoomException("방이 존재하지 않아요"));

        //방이 INACTIVE인 경우
        if(foundedRoom.getStatus() == Status.INACTIVE) {
            throw new RoomException("방이 비활성화상태에요");
        }

        return foundedRoom;
    }
}
