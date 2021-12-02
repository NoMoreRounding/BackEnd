package dku.globalsw.nomorerounding.auth.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExpiredTokenException extends BaseException {
    private static final String message = "유효기간이 만료된 토큰입니다.";

    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public ExpiredTokenException() {
        super(message, httpStatus);
    }
}
