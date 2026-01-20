package br.com.loteria.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class DezenaSorteioLotofacilDTO extends DezenaSorteioDTO {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private SorteioLotofacilDTO sorteioLotofacil;
}
