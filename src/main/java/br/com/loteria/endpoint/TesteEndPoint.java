package br.com.loteria.endpoint;

import br.com.loteria.security.service.Secured;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/teste")
@Api(value = "Testes", tags = {"testes"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TesteEndPoint {

    @GET
    @Path("/protegido")
    @Secured
    @ApiOperation(value = "EndPoint protegido por autenticação",
            authorizations = @Authorization(value = "Bearer"))
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Teste realizado com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Teste realizado com erro")
    })
    public Response endPointProtegido(@Context SecurityContext securityContext) {
        // Obtém o usuário autenticado
        String username = securityContext.getUserPrincipal().getName();

        // Ou obtém o objeto Usuario completo do contexto
        // Usuario usuario = (Usuario) requestContext.getProperty("usuario");

        return Response.ok()
                .entity("Olá " + username + ", você está autenticado!")
                .build();
    }

    @GET
    @Path("/nao-protegido")
    @ApiOperation(value = "EndPoint não protegido por autenticação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Teste realizado com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Teste realizado com erro")
    })
    public Response endPointNaoProtegido() {
        return Response.ok("Você não está autenticado! ").build();
    }

}
