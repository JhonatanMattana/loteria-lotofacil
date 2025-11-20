package br.com.loteria.dto;

import java.time.LocalDate;

import br.com.loteria.enums.TipoJogoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class ConcursoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Short numero;
	
	private LocalDate dataProximoConcurso;
	
	private Short numeroConcursoAnterior;

	private Short numeroConcursoProximo;
	
	private TipoJogoEnum tipoJogo;
	
	private SorteioDTO sorteio;
}
