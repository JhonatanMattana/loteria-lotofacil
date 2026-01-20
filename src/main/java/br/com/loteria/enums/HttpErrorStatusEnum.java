package br.com.loteria.enums;

public enum HttpErrorStatusEnum {
	BAD_REQUEST(400),
    NOT_FOUND(404),
    CONFLICT(409),
    UNPROCESSABLE_ENTITY(422),
    INTERNAL_ERROR(500);

	private final int statusCode;

    HttpErrorStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
