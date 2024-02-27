package tenshop.config.annotation.aspect.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseCommon {
    private int code;
    private String message;
    private boolean success;

    public ResponseCommon(HttpStatus code, String message, boolean success) {
        this.code = code.value();
        this.message = message;
        this.success = success;
    }

    public static ResponseCommon success() {
        return new ResponseCommon(HttpStatus.OK, "", true);
    }

    public static ResponseCommon notFoundError(String message) {
        return new ResponseCommon(HttpStatus.NOT_FOUND, message, false);
    }

    public static ResponseCommon internalServerError() {
        return new ResponseCommon(HttpStatus.INTERNAL_SERVER_ERROR, "관리자에게 문의해 주세요.", false);
    }
}


