package br.com.loteria.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificarSorteioSiteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public VerificarSorteioSiteDTO(String message) {
		this.message = message;
	}

	private String message;
}