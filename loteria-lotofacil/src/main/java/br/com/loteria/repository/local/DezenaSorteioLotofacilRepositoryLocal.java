package br.com.loteria.repository.local;

import java.util.List;

import br.com.loteria.entidade.DezenaSorteioLotofacil;
import br.com.loteria.enums.OrdenacaoDezenaLotofacilEnum;

public interface DezenaSorteioLotofacilRepositoryLocal {
	List<DezenaSorteioLotofacil> buscarDezenas(Short numeroConcurso, Short numeroConcursoDe,
			Short numeroConcursoAte, OrdenacaoDezenaLotofacilEnum ordenacao);
}