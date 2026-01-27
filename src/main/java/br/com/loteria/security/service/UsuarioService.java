package br.com.loteria.security.service;

import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.NotAuthorizedException;

import br.com.loteria.security.dto.UsuarioDTO;
import br.com.loteria.security.entidade.Usuario;
import br.com.loteria.security.repository.UsuarioRepository;

@Stateless
@LocalBean
public class UsuarioService {

    @EJB
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuario(String usuario) {
    	Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(usuario);
    	if (!usuarioOpt.isPresent()) {
            throw new NotAuthorizedException("Credenciais inválidas");
        }

        return usuarioOpt.get();
    }
    
    public Usuario getById(Long idUsuario) {
    	Optional<Usuario> usuaruoOptional = usuarioRepository.findById(idUsuario);
    	return usuaruoOptional.orElseThrow();
    }
    
    public List<Usuario> findAll() {
    	return usuarioRepository.findAll();
    }


    public Usuario criarNovo(UsuarioDTO usuario) {
        if (usuarioRepository.existsByUsuario(usuario.getUsuario())) {
            throw new IllegalArgumentException("Usuário já existe");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsuario(usuario.getUsuario());
        novoUsuario.setSenha(usuario.getSenha());

        return usuarioRepository.save(novoUsuario);
    }
    
    public void deletar(String usuario) {
    	if (!usuarioRepository.existsByUsuario(usuario)) {
            throw new IllegalArgumentException("Usuário não cadastrado no Sistema!!!");
        }
    	
    	Usuario usuarioEncontrado = usuarioRepository.findByUsuario(usuario).get();
    	
    	usuarioRepository.deletarUsuario(usuarioEncontrado.getId());
    }

}
