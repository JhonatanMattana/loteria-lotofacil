package br.com.loteria.security.endpoint;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.loteria.security.dto.UsuarioDTO;
import br.com.loteria.security.service.Secured;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IUsuarioEndPoint {
	
	@GET
	@Secured
	@ApiOperation(value = "EndPoint para buscar usuário")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Usuário")
    })
	public Response find(@ApiParam(value = "Usuário", required = true) @QueryParam("usuario") String usuario);
	
	@GET
	@Secured
	@Path(value = "/findbyid")
	@ApiOperation(value = "EndPoint para buscar usuário pelo seu ID")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Usuário")
    })
	public Response findById(@ApiParam(value = "ID do Usuário", required = true) @QueryParam("id") Long id);
	
	@GET
	@Secured
	@Path("/findall")
	@ApiOperation(value = "EndPoint para buscar todos os usuários")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Usuários encontrados com sucesso"),
	    @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
	    @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar usuários")
	})
	public Response findAll();
	
	@POST
	@Secured
	@ApiOperation(value = "EndPoint para criar novo usuário")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário criado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao criar Usuário")
    })
	public Response criarNovo(@ApiParam(value = "Usuário", required = true) @Valid @NotNull UsuarioDTO usuario);
	
	@DELETE
	@Secured
	@Path("/{usuario}")
	@ApiOperation(value = "EndPoint para deletar usuário")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário deletado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao deletar Usuário")
    })
	public Response deletar(@ApiParam(value = "Usuário", required = true) @PathParam("usuario") @Valid @NotEmpty String usuario);

}
