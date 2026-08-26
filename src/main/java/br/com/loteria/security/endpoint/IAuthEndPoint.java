package br.com.loteria.security.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.loteria.security.dto.LoginDTO;
import br.com.loteria.security.dto.TokenDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IAuthEndPoint {
	
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
    public Response login(@ApiParam(value = "Credenciais de login", required = true) LoginDTO loginDTO);
	
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
    		@HeaderParam("Authorization") String authHeader);

}