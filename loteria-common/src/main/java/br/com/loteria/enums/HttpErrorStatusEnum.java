package br.com.loteria.enums;

public enum HttpErrorStatusEnum {
	BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    CONFLICT(409, "CONFLICT"),
    UNPROCESSABLE_ENTITY(422, "UNPROCESSABLE_ENTITY"),
    INTERNAL_ERROR(500, "INTERNAL_ERROR");

	private final int statusCode;
	private final String descicao;

    HttpErrorStatusEnum(int statusCode, String descrico) {
        this.statusCode = statusCode;
        this.descicao = descrico;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public String getDescicao() {
		return descicao;
	}
}
