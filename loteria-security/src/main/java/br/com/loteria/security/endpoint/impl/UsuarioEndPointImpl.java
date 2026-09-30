package br.com.loteria.security.endpoint.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.exception.LoteriaException;
import br.com.loteria.security.dto.UsuarioDTO;
import br.com.loteria.security.endpoint.IUsuarioEndPoint;
import br.com.loteria.security.entidade.Usuario;
import br.com.loteria.security.service.UsuarioService;
import io.swagger.annotations.Api;

@Path("/usuario")
@Api(value = "Usuário", tags = {"Usuário"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioEndPointImpl implements IUsuarioEndPoint {
	
	@EJB
	private UsuarioService usuarioService;

	@Override
	public Response find(String usuario) {
		Usuario usuarioConsultado = usuarioService.getUsuario(usuario);
		return Response.ok(new UsuarioDTO(usuarioConsultado.getUsuario(), usuarioConsultado.getSenha())).build();
	}
	
	@Override
	public Response findById(Long id) {
		Usuario byId = usuarioService.getById(id);
		return Response.ok(new UsuarioDTO(byId.getUsuario(), byId.getSenha())).build();
	}
	
	@Override
	public Response findAll() {
		List<Usuario> usuarios = usuarioService.findAll();
		
		List<UsuarioDTO> dtos = usuarios.stream()
		        .map(u -> new UsuarioDTO(u.getUsuario(), u.getSenha()))
		        .toList();

	    return Response.ok(dtos).build();
	}

	
	@Override
	public Response criarNovo(UsuarioDTO usuario) {
		Usuario usuarioConsultado = usuarioService.criarNovo(usuario);
		return Response.ok(new UsuarioDTO(usuarioConsultado.getUsuario(), usuarioConsultado.getSenha())).build();
	}
	
	@Override
	public Response deletar(String usuario) {
		try {
			usuarioService.deletar(usuario);
			return Response.noContent().build();
		} catch (LoteriaException e) {
			throw new LoteriaException(e.getMessage(), e.getStatus());
		} catch (Exception e) {
			throw new LoteriaException(e.getMessage(), HttpErrorStatusEnum.INTERNAL_ERROR);
		}
	}
	
}
