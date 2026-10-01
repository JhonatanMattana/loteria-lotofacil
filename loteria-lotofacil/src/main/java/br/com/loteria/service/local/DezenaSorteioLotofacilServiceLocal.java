package br.com.loteria.service.local;

import java.util.List;

import br.com.loteria.dto.DezenaSorteioLotofacilDTO;
import br.com.loteria.enums.OrdenacaoDezenaLotofacilEnum;

public interface DezenaSorteioLotofacilServiceLocal {
	List<DezenaSorteioLotofacilDTO> buscarDezenas(Short numeroConcurso, Short numeroConcursoDe,
			Short numeroConcursoAte, OrdenacaoDezenaLotofacilEnum ordenacao);
}