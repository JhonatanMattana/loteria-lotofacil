package br.com.loteria.endpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.security.dto.LoginDTO;
import br.com.loteria.security.dto.TokenDTO;
import br.com.loteria.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/baixarresultado")
@Api(value = "Baixar Resultado", tags = {"Baixar Resultado"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixarResultadoEndPoint {

	@GET
    @ApiOperation(value = "EndPoint para baixar resultado dos jogos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Resultado baixado com sucesso"),
        @ApiResponse(code = 401, message = "Baixar resultado, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao baixar resultado")
    })
    public Response buscarUsuario(
            @ApiParam(value = "ID do teste", required = true) 
            @QueryParam("id") Long id) {
        return Response.ok("Olá mundo: " + id).build();
    }

}
