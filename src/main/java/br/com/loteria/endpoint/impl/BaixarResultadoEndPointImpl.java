package br.com.loteria.endpoint.impl;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import br.com.loteria.endpoint.IBaixarResultadoEndPoint;
import br.com.loteria.service.BaixarResultadoService;

public class BaixarResultadoEndPointImpl implements IBaixarResultadoEndPoint {
	
	@EJB
	private BaixarResultadoService baixarResultadoService;

	@Override
	public Response porNumeroConcurso(Integer numero) {
		baixarResultadoService.porNumeroConcurso(numero);
		return null;
	}

}
