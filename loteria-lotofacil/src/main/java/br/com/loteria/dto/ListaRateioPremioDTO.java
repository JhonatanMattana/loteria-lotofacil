package br.com.loteria.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaRateioPremioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer faixa;
	private String descricaoFaixa;
	private Integer numeroDeGanhadores;
	private BigDecimal valorPremio;
}
