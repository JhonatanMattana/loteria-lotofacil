package br.com.loteria.exception;

import java.io.Serial;
import java.io.Serializable;

import br.com.loteria.enums.HttpErrorStatusEnum;

public class LoteriaException extends RuntimeException implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private final HttpErrorStatusEnum status;
    private final int errorCode;

    public LoteriaException(String message, HttpErrorStatusEnum status) {
        super(message);
        this.status = status;
        this.errorCode = status.getStatusCode();
    }

    public HttpErrorStatusEnum getStatus() {
        return status;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
