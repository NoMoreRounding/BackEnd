package dku.globalsw.nomorerounding.auth.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidUserException extends BaseException {
    private final static String message = "회원 정보가 없습니다.";

    private final static HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public InvalidUserException() {
        super(message, httpStatus);
    }
}
