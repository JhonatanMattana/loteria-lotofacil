package br.com.loteria.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class CredenciaisInvalidasException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CredenciaisInvalidasException(String message) {
        super(message);
    }

}