package br.com.loteria.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class DezenaLotofacilDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Byte dezena;
	
	private Byte ordem;
	
	private SorteioDTO sorteio;
}
