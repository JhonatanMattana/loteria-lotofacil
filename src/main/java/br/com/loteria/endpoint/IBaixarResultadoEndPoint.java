package br.com.loteria.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/baixarresultado")
@Api(value = "Baixar Resultado", tags = {"Baixar Resultado"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IBaixarResultadoEndPoint {

	@GET
    @ApiOperation(value = "EndPoint para baixar resultado dos jogos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Resultado baixado com sucesso"),
        @ApiResponse(code = 401, message = "Baixar resultado, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao baixar resultado")
    })
    public Response porNumeroConcurso(
            @ApiParam(value = "Número do concurso", required = true) 
            @QueryParam("numero") Integer numero);

}
