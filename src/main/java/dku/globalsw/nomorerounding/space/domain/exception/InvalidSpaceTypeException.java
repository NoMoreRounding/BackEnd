package dku.globalsw.nomorerounding.space.domain.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidSpaceTypeException extends BaseException {

    private final static String message = "해당 주차 공간에 댈 수 있는 권한이 없습니다.";

    private final static HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public InvalidSpaceTypeException() {
        super(message, httpStatus);
    }
}