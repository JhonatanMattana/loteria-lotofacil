package br.com.loteria.exception;

import java.io.Serial;
import java.io.Serializable;

public class ApiErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private String errorCode;
    private int status;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String message, String errorCode, int status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatus() {
        return status;
    }

}
