package br.com.loteria.service.local;

import br.com.loteria.dto.SorteioLotofacilDTO;

public interface SorteioLotofacilServiceLocal {
	SorteioLotofacilDTO buscarSorteioPorNumero(Short numeroConcurso);
	SorteioLotofacilDTO buscarUltimoSorteioRealizado();
}