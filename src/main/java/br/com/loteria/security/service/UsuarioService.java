package br.com.loteria.security.service;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.NotAuthorizedException;

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

    public Usuario criarNovo(String usuario, String senha) {
        if (usuarioRepository.existsByUsuario(usuario)) {
            throw new IllegalArgumentException("Usuário já existe");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsuario(usuario);
        novoUsuario.setSenha(senha);

        return usuarioRepository.save(novoUsuario);
    }

}
