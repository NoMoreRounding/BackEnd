package dku.globalsw.nomorerounding.space.domain.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicatedSpaceException extends BaseException {

    private final String message;

    private final static HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public DuplicatedSpaceException(String message) {
        super(message, httpStatus);
        this.message = message;
    }

}