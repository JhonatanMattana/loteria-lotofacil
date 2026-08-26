package br.com.loteria.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class CredenciaisInvalidasException extends RuntimeException {

    public CredenciaisInvalidasException(String message) {
        super(message);
    }

}