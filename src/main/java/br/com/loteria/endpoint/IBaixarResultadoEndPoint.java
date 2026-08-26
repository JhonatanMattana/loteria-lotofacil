package br.com.loteria.endpoint;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

public interface IBaixarResultadoEndPoint {

	@GET
	@Secured
	@Path("/verificarsorteio")
	@ApiOperation(value = "EndPoint para verificar se houve sorteio")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sorteio verificado com sucesso"),
        @ApiResponse(code = 401, message = "Verificar Sorteio, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao verificar sorteio")
    })
	public Response verificarSorteioSite(
			@ApiParam(value = "Número do concurso", required = true)
			@QueryParam("numeroConcurso") @Valid @NotNull Integer numeroConcurso,
			@ApiParam(value = "Modalidade concurso", required = true)
			@QueryParam("modalidade") @Valid @NotBlank String modalidade);

	@GET
	@Secured
	@Path("/pornumeroconcurso")
	@ApiOperation(value = "EndPoint para baixar resultado dos jogos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Resultado baixado com sucesso"),
			@ApiResponse(code = 401, message = "Baixar resultado, acesso não autorizado"),
			@ApiResponse(code = 404, message = "Ocorreu um erro ao baixar resultado")
	})
	public Response porNumeroConcurso(
			@ApiParam(value = "Número do concurso", required = true)
			@QueryParam("numeroConcurso") @Valid @NotNull Integer numeroConcurso,
			@ApiParam(value = "Modalidade concurso", required = true)
			@QueryParam("modalidade") @Valid @NotBlank String modalidade);

}