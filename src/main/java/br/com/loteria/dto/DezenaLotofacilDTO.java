package br.com.loteria.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class DezenaLotofacilDTO extends BaseDTO {

	private Long id;
	
	private Byte dezena;
	
	private Byte ordem;
}
