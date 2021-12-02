package dku.globalsw.nomorerounding.space.domain.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidSpaceException extends BaseException {
    private final static String message = "일치하는 주차 정보가 없습니다.";

    private final static HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public InvalidSpaceException() {
        super(message, httpStatus);
    }
}