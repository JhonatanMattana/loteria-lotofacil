package br.com.loteria.service.local;

import br.com.loteria.dto.BaixarResultadoDTO;
import br.com.loteria.dto.VerificarSorteioSiteDTO;

public interface BaixarResultadoServiceLocal {
	BaixarResultadoDTO porNumeroConcurso(Integer numero, String modalidade);
	VerificarSorteioSiteDTO verificarSorteioSite(Integer numeroConcurso, String modalidade);
	void salvarResultado(BaixarResultadoDTO dto);
}