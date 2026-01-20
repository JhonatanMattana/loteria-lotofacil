package br.com.loteria.dto;

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
public class SorteioLotofacilDTO extends SorteioDTO {

	private static final long serialVersionUID = 1L;

	private Long id;

	private List<DezenaSorteioLotofacilDTO> dezenasLotofacil;

	public void addDezenasLotofacil(DezenaSorteioLotofacilDTO dezena) {
		if (this.dezenasLotofacil == null) {
			this.dezenasLotofacil = new ArrayList<>();
		}
		dezena.setSorteioLotofacil(this);
		this.dezenasLotofacil.add(dezena);
	}

	public void removeDezena(DezenaSorteioLotofacilDTO dezena) {
		dezena.setSorteioLotofacil(null);
		this.dezenasLotofacil.remove(dezena);
	}

}
