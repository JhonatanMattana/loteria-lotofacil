package br.com.loteria.endpoint;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.loteria.security.service.Secured;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ISorteioLotofacilEndPoint {
	
	@GET
	@Secured
	@Path("/buscar-por-numero")
	@ApiOperation(value = "EndPoint para buscar Sorteio Lotofacil por número sorteio")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sorteio Lotofacil encontrado com sucesso"),
        @ApiResponse(code = 401, message = "Sorteio Lotofacil, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Sorteio Lotofacil")
    })
	public Response buscarSorteioPorNumero(
			@ApiParam(value = "Número do concurso", required = true)
			@QueryParam("numeroConcurso") @Valid @NotNull Short numero);
	
	@GET
	@Secured
	@Path("/buscar-ultimo-sorteio")
	@ApiOperation(value = "EndPoint para buscar Último Sorteio Lotofacil realizado")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Último Sorteio Lotofacil encontrado com sucesso"),
        @ApiResponse(code = 401, message = "Último Sorteio Lotofacil, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Último Sorteio Lotofacil realizado")
    })
	public Response buscarUltimoSorteioRealizado();
	
}