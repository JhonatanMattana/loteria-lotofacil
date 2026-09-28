package br.com.loteria.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.loteria.dto.BaixarResultadoDTO;
import br.com.loteria.dto.VerificarSorteioSiteDTO;
import br.com.loteria.repository.BaixarResultadoRepository;
import br.com.loteria.util.client.LoteriaClient;

@Stateless
public class BaixarResultadoService {
	
	@EJB
	private BaixarResultadoRepository baixarResultadoRepository;
	
	@EJB
	private LoteriaClient lotofacilClient;
	
	public BaixarResultadoDTO porNumeroConcurso(Integer numero, String modalidade) {
		return lotofacilClient.buscarResultado(numero, modalidade);
	}

	public VerificarSorteioSiteDTO verificarSorteioSite(Integer numeroConcurso, String modalidade) {
		try {
			lotofacilClient.verificarSorteioSite(numeroConcurso, modalidade);
			String message = String.format("Sorteio da %s, número %d, está disponível!", modalidade, numeroConcurso);
			return new VerificarSorteioSiteDTO(message);
		} catch (Exception e) {
			String message = String.format("Sorteio da %s, número %d, não está disponível!", modalidade, numeroConcurso);
			return new VerificarSorteioSiteDTO(message);
		}
	}

}