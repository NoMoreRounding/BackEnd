package dku.globalsw.nomorerounding.auth.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CoreException extends BaseException {

    private final String message;

    private final static HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public CoreException(String message) {
        super(message, httpStatus);
        this.message = message;
    }
}
