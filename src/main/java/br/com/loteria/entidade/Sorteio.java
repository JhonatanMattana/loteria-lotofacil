package br.com.loteria.entidade;

import java.time.LocalDate;
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
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "SORTEIO")
public class Sorteio extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "LOCALSORTEIO")
	private String localSorteio;

	@Column(name = "NOMEMUNICIPIOUFSORTEIO")
	private String nomeMunicipioUFSorteio;
	
	@Column(name = "DATAAPURACAO")
	private LocalDate dataApuracao;
	
	@OneToOne(mappedBy = "sorteio", fetch = FetchType.LAZY)
	private Concurso concurso;

	@OneToMany(mappedBy = "sorteio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DezenaLotofacil> dezenasLotofacil;
	
	public void addDezenasLotofacil(DezenaLotofacil dezena) {
		if (this.dezenasLotofacil == null) {
			this.dezenasLotofacil = new ArrayList<>();
		}
		dezena.setSorteio(this);
		this.dezenasLotofacil.add(dezena);
	}
	
	public void removeDezena(DezenaLotofacil dezena) {
	    dezena.setSorteio(null);
	    this.dezenasLotofacil.remove(dezena);
	}

}
