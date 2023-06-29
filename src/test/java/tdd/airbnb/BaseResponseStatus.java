package tdd.airbnb;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    // UserException
    DUPLICATED_EMAIL(false, 2010, "중복된 이메일입니다."),
    NONE_EXIST_USER(false, 2011, "존재하지 않는 회원입니다."),
    INVALID_EMAIL_OR_PASSWORD(false, 2012, "이메일 혹은 비밀번호가 잘못되었습니다."),

    // RoomException
    NONE_ROOM(false, 2021, "존재하지 않는 숙소입니다."),
    INACTIVE_ROOM(false, 2022, "비공개 숙소입니다."),
    ;

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;
}
