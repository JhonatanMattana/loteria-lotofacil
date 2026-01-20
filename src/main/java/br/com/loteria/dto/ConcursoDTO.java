package br.com.loteria.dto;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

import br.com.loteria.enums.ModalidadeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class ConcursoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Short numero;
	
	private LocalDate dataProximoConcurso;
	
	private Short numeroConcursoAnterior;

	private Short numeroConcursoProximo;
	
	private ModalidadeEnum modalidade;
	
}
