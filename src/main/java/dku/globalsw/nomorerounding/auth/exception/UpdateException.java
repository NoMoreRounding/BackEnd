package dku.globalsw.nomorerounding.auth.exception;

import dku.globalsw.nomorerounding.base.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UpdateException extends BaseException {
    private final static String message = "수정할 정보가 없습니다.";

    private final static HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public UpdateException() {
        super(message, httpStatus);
    }
}