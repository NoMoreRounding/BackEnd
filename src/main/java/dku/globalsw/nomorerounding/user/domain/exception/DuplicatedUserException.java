package dku.globalsw.nomorerounding.user.domain.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicatedUserException extends BaseException {

    private final static String message = "이미 가입된 유저입니다.";

    private final static HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public DuplicatedUserException() {
        super(message, httpStatus);
    }

}
