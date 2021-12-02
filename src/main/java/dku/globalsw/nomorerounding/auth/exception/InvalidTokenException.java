package dku.globalsw.nomorerounding.auth.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTokenException extends BaseException {
    private final static String message = "유효하지 않은 토큰입니다";

    private final static HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public InvalidTokenException() {
        super(message, httpStatus);
    }
}
