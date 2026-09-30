package br.com.loteria.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.loteria.enums.ModalidadeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode( of = { "numero" } )
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaixarResultadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Short numero;
	
	private boolean acumulado;
	
    private List<Byte> listaDezenas;
    
    private List<Byte> dezenasSorteadasOrdemSorteio;
    
    private ModalidadeEnum modalidade;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataProximoConcurso;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataApuracao;
    
    private String diaApuracao;
	
	private Short numeroConcursoAnterior;

	private Short numeroConcursoProximo;
	
	private BigDecimal valorAcumuladoConcursoEspecial;
	
	private BigDecimal valorAcumuladoProximoConcurso;
	
	private BigDecimal valorArrecadado;
	
	private BigDecimal valorEstimadoProximoConcurso;
	
	private String localSorteio;
	
	private String nomeMunicipioUFSorteio;
	
}