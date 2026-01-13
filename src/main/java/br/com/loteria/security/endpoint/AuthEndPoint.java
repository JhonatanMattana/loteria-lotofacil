package br.com.loteria.security.endpoint;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.security.dto.LoginDTO;
import br.com.loteria.security.dto.TokenDTO;
import br.com.loteria.security.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/auth")
@RequestScoped
@Api(value = "Autenticação", tags = {"Autenticação"})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthEndPoint {

    private static final Logger LOGGER = Logger.getLogger(AuthEndPoint.class.getName());

    @Inject
    private AuthService authService;

    @POST
    @Path("/login")
    @ApiOperation(
            value = "Realiza login e retorna token JWT",
            notes = "Use as credenciais: usuario='admin', senha='123'",
            response = TokenDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login realizado com sucesso", response = TokenDTO.class),
            @ApiResponse(code = 400, message = "Dados inválidos"),
            @ApiResponse(code = 401, message = "Credenciais inválidas"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public Response login(
            @ApiParam(value = "Credenciais de login", required = true)
            LoginDTO loginDTO) {

        try {
            LOGGER.log(Level.INFO, "Tentativa de login para usuário: {0}", loginDTO.getUsuario());

            TokenDTO tokenDTO = authService.autenticar(loginDTO);

            LOGGER.log(Level.INFO, "Login realizado com sucesso para: {0}", loginDTO.getUsuario());

            return Response.ok(tokenDTO).build();

        } catch (NotAuthorizedException e) {
            LOGGER.log(Level.WARNING, "Login falhou: {0}", e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(criarMensagemErro("Credenciais inválidas"))
                    .build();

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Dados inválidos: {0}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(criarMensagemErro(e.getMessage()))
                    .build();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar login", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(criarMensagemErro("Erro interno do servidor"))
                    .build();
        }
    }

    @GET
    @Path("/validar")
    @ApiOperation(
            value = "Valida um token JWT",
            notes = "Retorna informações do usuário se o token for válido"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Token válido"),
            @ApiResponse(code = 401, message = "Token inválido")
    })
    public Response validarToken(
            @ApiParam(value = "Token JWT", required = true)
            @HeaderParam("Authorization") String authHeader) {

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new NotAuthorizedException("Token ausente");
            }

            String token = authHeader.substring("Bearer ".length());
            var usuario = authService.validarToken(token);

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("valido", true);
            resposta.put("usuario", usuario.getUsuario());
            resposta.put("senha", usuario.getSenha());
            resposta.put("id", usuario.getId());

            return Response.ok(resposta).build();

        } catch (NotAuthorizedException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(criarMensagemErro(e.getMessage()))
                    .build();
        }
    }

    /**
     * Helper para criar mensagens de erro padronizadas
     */
    private Map<String, String> criarMensagemErro(String mensagem) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", mensagem);
        erro.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return erro;
    }

}