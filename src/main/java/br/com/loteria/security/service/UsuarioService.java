package br.com.loteria.security.service;

import br.com.loteria.security.dto.LoginDTO;
import br.com.loteria.security.dto.TokenDTO;
import br.com.loteria.security.entidade.Usuario;
import br.com.loteria.security.repository.UsuarioRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.NotAuthorizedException;
import java.util.Optional;

@Stateless
@LocalBean
public class UsuarioService {

    @EJB
    private UsuarioRepository usuarioRepository;

    public Usuario getFindUsuario(LoginDTO dto) {
        usuarioRepository.findByUsuario(dto.getUsuario());

        return null;
    }

    public TokenDTO autenticar(LoginDTO loginDTO) {
        validarLoginDTO(loginDTO);

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(loginDTO.getUsuario());

        if (!usuarioOpt.isPresent()) {
            throw new NotAuthorizedException("Credenciais inválidas");
        }

        Usuario usuario = usuarioOpt.get();

        if (!validarSenha(loginDTO.getSenha(), usuario.getSenha())) {
            throw new NotAuthorizedException("Credenciais inválidas");
        }

        String token = TokenService.gerarToken(usuario.getUsuario());

        return new TokenDTO(token);
    }

    public Usuario validarToken(String token) {
        try {
            String userName = TokenService.validarToken(token);

            Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(userName);

            if (!usuarioOpt.isPresent()) {
                throw new NotAuthorizedException("Usuário não encontrado");
            }

            return usuarioOpt.get();
        } catch (Exception e) {
            throw new NotAuthorizedException("Token inválido: " + e.getMessage());
        }
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

    private void validarLoginDTO(LoginDTO loginDTO) {
        if (loginDTO == null) {
            throw new IllegalArgumentException("LoginDTO não pode ser nulo");
        }
        if (loginDTO.getUsuario() == null || loginDTO.getUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("Usuário não pode ser vazio");
        }
        if (loginDTO.getSenha() == null || loginDTO.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
    }

    private boolean validarSenha(String senhaInformada, String senhaArmazenada) {
        return senhaInformada.equals(senhaArmazenada);
    }
}
