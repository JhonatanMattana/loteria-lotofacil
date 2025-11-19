package br.com.loteria.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SorteioDTO extends BaseDTO {

	private Long id;
	
	private String localSorteio;

	private String nomeMunicipioUFSorteio;
	
	private LocalDate dataApuracao;

	private ConcursoDTO concurso;
	
	private List<DezenaLotofacilDTO> dezenasLotofacil;
	
	public void addDezenasLotofacil(DezenaLotofacilDTO dezena) {
		if (this.dezenasLotofacil == null) {
			this.dezenasLotofacil = new ArrayList<>();
		}
		this.dezenasLotofacil.add(dezena);
	}
}
