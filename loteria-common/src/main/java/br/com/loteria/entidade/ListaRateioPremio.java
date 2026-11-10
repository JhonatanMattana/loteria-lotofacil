package br.com.loteria.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class ListaRateioPremio extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "FAIXA", nullable = false)
	private Integer faixa;

	@Column(name = "DESCRICAO_FAIXA", nullable = false)
	private String descricaoFaixa;

	@Column(name = "NUMERO_DE_GANHADORES", nullable = false)
	private Integer numeroDeGanhadores;

	@Column(name = "VALOR_PREMIO", precision = 15, scale = 2)
	private BigDecimal valorPremio;
}
