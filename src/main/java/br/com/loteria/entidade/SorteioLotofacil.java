package br.com.loteria.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "SORTEIOLOTOFACIL", uniqueConstraints = {
		@UniqueConstraint(
				name = "UK_SORTEIOLF_NUMERO_MODALIDADE",
	            columnNames = {"NUMERO", "MODALIDADE"})
})
public class SorteioLotofacil extends Sorteio {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "sorteioLotofacil", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DezenaSorteioLotofacil> dezenasLotofacil;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
	    name = "ID_CONCURSOLOTOFACIL",
	    foreignKey = @ForeignKey(name = "FK_Sorteio_Concurso"),
	    nullable = false,
	    unique = true
	)
	private ConcursoLotofacil concursoLotofacil;
	
	public void addDezenasLotofacil(DezenaSorteioLotofacil dezena) {
		if (this.dezenasLotofacil == null) {
			this.dezenasLotofacil = new ArrayList<>();
		}
		dezena.setSorteioLotofacil(this);
		this.dezenasLotofacil.add(dezena);
	}
	
	public void removeDezena(DezenaSorteioLotofacil dezena) {
	    dezena.setSorteioLotofacil(null);
	    this.dezenasLotofacil.remove(dezena);
	}

}
