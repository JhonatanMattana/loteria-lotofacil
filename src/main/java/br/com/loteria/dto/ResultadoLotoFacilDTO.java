package br.com.loteria.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoLotoFacilDTO {
	private Integer numero;
    private List<String> listaDezenas;
    private String dataApuracao;
}
