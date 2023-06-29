package tdd.airbnb.room;

import org.apache.ibatis.annotations.Mapper;
import tdd.airbnb.room.dto.RoomDetailResponseDto;
import tdd.airbnb.room.dto.RoomListResponseDto;

import java.util.List;
import java.util.Optional;

/**
 * 룸 개별 조회, 룸 전체 리스트 조회
 */
@Mapper
public interface RoomMapper {


    List<RoomListResponseDto> findAllRooms();


    Optional<RoomDetailResponseDto> findRoomByRoomId(Long roomId);
}
