package dku.globalsw.nomorerounding.base.exception;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private LocalTime timeStamp;
    private String message;
    private int status;
    private String code;
}
