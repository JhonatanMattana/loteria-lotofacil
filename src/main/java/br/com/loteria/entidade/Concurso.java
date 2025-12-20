package br.com.loteria.entidade;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.loteria.enums.TipoJogoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "CONCURSO")
public class Concurso extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NUMERO", nullable = false)
	private Short numero;
	
	@Column(name = "DATAPROXIMOCONCURSO")
	private LocalDate dataProximoConcurso;
	
	@Column(name = "NUMEROCONCURSOANTERIOR")
	private Short numeroConcursoAnterior;

	@Column(name = "NUMEROCONCURSOPROXIMO")
	private Short numeroConcursoProximo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPOJOGO", nullable = false)
	private TipoJogoEnum tipoJogo;

}
