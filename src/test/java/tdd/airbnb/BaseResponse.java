package tdd.airbnb;

import lombok.Getter;

@Getter
public class BaseResponse<T> {

    private final Boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;

    private final T result;

    //Request Success, Request 성공시 무조건 result 존재
    public BaseResponse(T result) {
        this.isSuccess = BaseResponseStatus.SUCCESS.isSuccess();
        this.responseCode = BaseResponseStatus.SUCCESS.getResponseCode();
        this.responseMessage = BaseResponseStatus.SUCCESS.getResponseMessage();
        this.result = result;
    }

    //Common Exception, 보내는 데이터 없이 메세지만 출력하면 되는 에러
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.responseCode = status.getResponseCode();
        this.responseMessage = status.getResponseMessage();
        this.result = null;
    }


}
