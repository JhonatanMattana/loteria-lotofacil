package br.com.loteria.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.loteria.security.service.Secured;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IDezenaSorteioLotofacilEndPoint {
	
	@GET
	@Secured
	@Path("/buscar-dezenas-ordem-sorteio")
	@ApiOperation(value = "EndPoint para buscar dezenas dos jogos ordenadas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Dezenas Sorteio Lotofacil encontradas com sucesso"),
			@ApiResponse(code = 401, message = "Dezena Sorteio Lotofacil, acesso não autorizado"),
			@ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Dezenas Sorteio Lotofacil")
	})
	public Response ordemSorteio(
			@QueryParam("numeroConcurso") Short numeroConcurso,
			@QueryParam("numeroConcursoDe") Short numeroConcursoDe,
			@QueryParam("numeroConcursoAte") Short numeroConcursoAte);
	
	@GET
	@Secured
	@Path("/buscar-dezenas-ordenadas-asc")
	@ApiOperation(value = "EndPoint para buscar dezenas dos jogos ordenadas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Dezenas Sorteio Lotofacil encontradas com sucesso"),
			@ApiResponse(code = 401, message = "Dezena Sorteio Lotofacil, acesso não autorizado"),
			@ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Dezenas Sorteio Lotofacil")
	})
	public Response ordenadasAscendentes(
			@QueryParam("numeroConcurso") Short numeroConcurso,
			@QueryParam("numeroConcursoDe") Short numeroConcursoDe,
			@QueryParam("numeroConcursoAte") Short numeroConcursoAte);
	
	@GET
	@Secured
	@Path("/buscar-dezenas-ordenadas-desc")
	@ApiOperation(value = "EndPoint para buscar dezenas dos jogos ordenadas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Dezenas Sorteio Lotofacil encontradas com sucesso"),
			@ApiResponse(code = 401, message = "Dezena Sorteio Lotofacil, acesso não autorizado"),
			@ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Dezenas Sorteio Lotofacil")
	})
	public Response ordenadasDescendentes(
			@QueryParam("numeroConcurso") Short numeroConcurso,
			@QueryParam("numeroConcursoDe") Short numeroConcursoDe,
			@QueryParam("numeroConcursoAte") Short numeroConcursoAte);
	
}