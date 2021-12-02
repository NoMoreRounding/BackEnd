package dku.globalsw.nomorerounding.lot.domain.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidFloorException extends BaseException {
    private final static String message = "주차장 정보가 없습니다.";

    private final static HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public InvalidFloorException() {
        super(message, httpStatus);
    }
}