package br.com.loteria.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
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
@Table(name = "CONCURSOLOTOFACIL", uniqueConstraints = {
		@UniqueConstraint(
				name = "UK_CONCURSOLF_NUM_MODALIDADE",
	            columnNames = {"NUMERO", "MODALIDADE"})
})
public class ConcursoLotofacil extends Concurso {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TEVE_GANHADOR_PREMIO_PRINCIPAL")
	private Boolean teveGanhadorPremioPrincipal;

	@OneToMany(mappedBy = "concursoLotofacil", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("faixa ASC")
	private List<ListaRateioPremioLotofacil> listaRateioPremioLotofacil;

	@OneToOne(
	    mappedBy = "concursoLotofacil",
	    fetch = FetchType.LAZY,
	    cascade = CascadeType.ALL,
	    orphanRemoval = true,
	    optional = true
	)
	private SorteioLotofacil sorteioLotofacil;

	public void addRateioPremioLotofacil(ListaRateioPremioLotofacil rateioPremio) {
		if (listaRateioPremioLotofacil == null) {
			listaRateioPremioLotofacil = new ArrayList<>();
		}
		rateioPremio.setConcursoLotofacil(this);
		listaRateioPremioLotofacil.add(rateioPremio);
	}
}