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
@EqualsAndHashCode(of = "id", callSuper = false)
public class SorteioDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

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
		dezena.setSorteio(this);
		this.dezenasLotofacil.add(dezena);
	}
	
	public void removeDezena(DezenaLotofacilDTO dezena) {
		dezena.setSorteio(null);
	    this.dezenasLotofacil.remove(dezena);
	}
}
