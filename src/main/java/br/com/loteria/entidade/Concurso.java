package br.com.loteria.entidade;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import br.com.loteria.enums.ModalidadeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Concurso extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "NUMERO", nullable = false)
	private Short numero;
	
	@Column(name = "DATAPROXIMOCONCURSO")
	private LocalDate dataProximoConcurso;
	
	@Column(name = "NUMEROCONCURSOANTERIOR")
	private Short numeroConcursoAnterior;

	@Column(name = "NUMEROCONCURSOPROXIMO")
	private Short numeroConcursoProximo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MODALIDADE", nullable = false)
	private ModalidadeEnum modalidade;
	
}
