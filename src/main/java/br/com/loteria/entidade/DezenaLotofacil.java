package br.com.loteria.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DEZENALOTOFACIL")
public class DezenaLotofacil extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DEZENA", nullable = false)
	private Byte dezena;
	
	@Column(name = "ORDEM")
	private Byte ordem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SORTEIO", foreignKey = @ForeignKey(name = "FK_DezenaLotofacil_Sorteio"))
	private Sorteio sorteio;
}
