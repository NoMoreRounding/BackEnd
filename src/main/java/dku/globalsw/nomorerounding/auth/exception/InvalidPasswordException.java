package dku.globalsw.nomorerounding.auth.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidPasswordException extends BaseException {
    private final static String message = "비밀번호가 틀렸습니다";

    private final static HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public InvalidPasswordException() {
        super(message, httpStatus);
    }
}