package br.com.loteria.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.loteria.dto.ResultadoLotoFacilDTO;
import br.com.loteria.repository.BaixarResultadoRepository;
import br.com.loteria.util.client.LotofacilClient;

@Stateless
public class BaixarResultadoService {
	
	@EJB
	private BaixarResultadoRepository baixarResultadoRepository;
	
	@EJB
	private LotofacilClient lotofacilClient;
	
	public ResultadoLotoFacilDTO porNumeroConcurso(Integer numero) {
		return lotofacilClient.buscarResultado(numero);
	}

}
