package br.com.loteria.exception;

public class LoteriaFormatMessage {
	private LoteriaFormatMessage() { }

    public static String formatter(String message, Object ...params ) {
        return String.format(message, params);
    }
}
