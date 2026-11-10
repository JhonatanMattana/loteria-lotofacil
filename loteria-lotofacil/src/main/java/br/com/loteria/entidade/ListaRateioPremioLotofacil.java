package br.com.loteria.entidade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "LISTARATEIOPREMIOLOTOFACIL", uniqueConstraints = {
		@UniqueConstraint(
				name = "UK_RATEIOPREMIO_LOTOFACIL_FAIXA",
				columnNames = {"ID_CONCURSOLOTOFACIL", "FAIXA"})
})
public class ListaRateioPremioLotofacil extends ListaRateioPremio {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
			name = "ID_CONCURSOLOTOFACIL",
			nullable = false,
			foreignKey = @ForeignKey(name = "FK_RATEIO_PREMIO_CONCURSO_LOTOFACIL")
	)
	private ConcursoLotofacil concursoLotofacil;
}
