package br.com.loteria.security.endpoint;

import java.util.List;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.security.dto.UsuarioDTO;
import br.com.loteria.security.entidade.Usuario;
import br.com.loteria.security.service.Secured;
import br.com.loteria.security.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/usuario")
@Api(value = "Usuário", tags = {"usuário"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioEndPoint {
	
	@EJB
	private UsuarioService usuarioService;

	@GET
	@Secured
	@ApiOperation(value = "EndPoint para buscar usuário")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Usuário")
    })
	public Response find(
			@ApiParam(value = "Usuário", required = true) @QueryParam("usuario") String usuario) {
		Usuario usuarioConsultado = usuarioService.getUsuario(usuario);
		return Response.ok(new UsuarioDTO(usuarioConsultado.getUsuario(), usuarioConsultado.getSenha())).build();
	}
	
	@GET
	@Secured
	@Path(value = "/byid")
	@ApiOperation(value = "EndPoint para buscar usuário pelo seu ID")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar Usuário")
    })
	public Response findById(
			@ApiParam(value = "ID do Usuário", required = true) @QueryParam("id") Long id) {
		Usuario byId = usuarioService.getById(id);
		return Response.ok(new UsuarioDTO(byId.getUsuario(), byId.getSenha())).build();
	}
	
	@GET
	@Secured
	@Path("/all")
	@ApiOperation(value = "EndPoint para buscar todos os usuários")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Usuários encontrados com sucesso"),
	    @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
	    @ApiResponse(code = 404, message = "Ocorreu um erro ao buscar usuários")
	})
	public Response findAll() {

		List<Usuario> usuarios = usuarioService.findAll();
		
		List<UsuarioDTO> dtos = usuarios.stream()
		        .map(u -> new UsuarioDTO(u.getUsuario(), u.getSenha()))
		        .toList();

	    return Response.ok(dtos).build();
	}

	@POST
	@Secured
	@ApiOperation(value = "EndPoint para criar novo usuário")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário criado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao criar Usuário")
    })
	public Response criarNovo(
			@ApiParam(value = "Usuário", required = true) @PathParam("usuario") @Valid @NotNull UsuarioDTO usuario) {
		Usuario usuarioConsultado = usuarioService.criarNovo(usuario);
		return Response.ok(new UsuarioDTO(usuarioConsultado.getUsuario(), usuarioConsultado.getSenha())).build();
	}
	
	@DELETE
	@Secured
	@ApiOperation(value = "EndPoint para deletar usuário")
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário deletado com sucesso"),
        @ApiResponse(code = 401, message = "Usuário, acesso não autorizado"),
        @ApiResponse(code = 404, message = "Ocorreu um erro ao deletar Usuário")
    })
	public Response deletar(
			@ApiParam(value = "Usuário", required = true) @PathParam("usuario") @Valid @NotEmpty String usuario) {
		usuarioService.deletar(usuario);
		return Response.noContent().build();
	}
	
}
