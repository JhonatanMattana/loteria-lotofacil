package br.com.loteria.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaixarResultadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numero;
    private List<Integer> listaDezenas;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataApuracao;
}