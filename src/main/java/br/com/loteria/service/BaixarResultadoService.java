package br.com.loteria.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.loteria.repository.BaixarResultadoRepository;

@Stateless
public class BaixarResultadoService {
	
	@EJB
	private BaixarResultadoRepository baixarResultadoRepository;

	public void porNumeroConcurso(Integer numero) {
		
	}

	
	
}
