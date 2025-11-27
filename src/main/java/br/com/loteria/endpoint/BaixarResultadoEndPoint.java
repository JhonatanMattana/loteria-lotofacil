package br.com.loteria.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/teste")
@Api(value = "Testes", tags = {"testes"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixarResultadoEndPoint {

	@GET
    @Path("/{id}")
    @ApiOperation(value = "EndPoint para Teste se a app esta funcionando")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Teste realizado com sucesso"),
        @ApiResponse(code = 404, message = "Teste realizado com erro")
    })
    public Response buscarUsuario(
            @ApiParam(value = "ID do teste", required = true) 
            @PathParam("id") Long id) {
        return Response.ok("Olá mundo").build();
    }
	
}
