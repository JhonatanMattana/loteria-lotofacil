package br.com.loteria.exception;

import java.io.Serial;
import java.io.Serializable;

public class ApiErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private int errorCode;
    private String statusDesc;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String message, int errorCode, String statusDesc) {
        this.message = message;
        this.errorCode = errorCode;
        this.statusDesc = statusDesc;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getStatusString() {
        return statusDesc;
    }

}
