package dku.globalsw.nomorerounding.lot.domain.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicatedFloorException extends BaseException {

    private final static String message = "이미 생성된 층입니다.";

    private final static HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public DuplicatedFloorException() {
        super(message, httpStatus);
    }

}
