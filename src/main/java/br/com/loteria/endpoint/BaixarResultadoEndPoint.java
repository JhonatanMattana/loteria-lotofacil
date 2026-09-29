package br.com.loteria.endpoint;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.dto.BaixarResultadoDTO;
import br.com.loteria.dto.VerificarSorteioSiteDTO;
import br.com.loteria.service.BaixarResultadoService;
import io.swagger.annotations.Api;

@Path("/baixar-resultado")
@Api(value = "Baixar Resultado", tags = {"Baixar Resultado"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixarResultadoEndPoint implements IBaixarResultadoEndPoint {
	
	@EJB
	private BaixarResultadoService baixarResultadoService;

	@Override
	public Response verificarSorteioSite(Integer numeroConcurso, String modalidade) {
		VerificarSorteioSiteDTO resultado = baixarResultadoService.verificarSorteioSite(numeroConcurso, modalidade);
		return Response.ok(resultado).build();
	}

	@Override
    public Response porNumeroConcurso(Integer numeroConcurso, String modalidade) {
		BaixarResultadoDTO porNumeroConcurso = baixarResultadoService.porNumeroConcurso(numeroConcurso, modalidade);
		return Response.ok(porNumeroConcurso).build();
	}

	@Override
	public Response salvarResultado(BaixarResultadoDTO baixarResultadoDTO) {
		baixarResultadoService.salvarResultado(baixarResultadoDTO);
	    return Response.noContent().build();
	}

}