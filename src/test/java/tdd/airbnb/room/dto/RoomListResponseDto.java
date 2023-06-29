package tdd.airbnb.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomListResponseDto {
    private long roomId;
    private String city;
    private String district;
    private int price;
    private String roomMainImage;
//    private double reviewAverageScore;
//    private int reviewCount;
}
