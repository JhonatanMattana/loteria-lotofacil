package br.com.loteria.security.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.NotAuthorizedException;

import br.com.loteria.security.dto.LoginDTO;
import br.com.loteria.security.dto.TokenDTO;
import br.com.loteria.security.entidade.Usuario;

@Stateless
@LocalBean
public class AuthService {

    @EJB
    private UsuarioService usuarioService;

    public String login(LoginDTO dto) {
        String token = null;

        Usuario usuarioConsultado = usuarioService.getUsuario(dto.getUsuario());
        if (usuarioConsultado != null) {
            token = TokenService.gerarToken(dto.getUsuario());
        }

        return token;
    }
    
    public TokenDTO autenticar(LoginDTO loginDTO) {
        validarLoginDTO(loginDTO);

        Usuario usuario = usuarioService.getUsuario(loginDTO.getUsuario());

        if (!validarSenha(loginDTO.getSenha(), usuario.getSenha())) {
            throw new NotAuthorizedException("Credenciais inválidas");
        }

        String token = TokenService.gerarToken(usuario.getUsuario());

        return new TokenDTO(token);
    }
    
    public Usuario validarToken(String token) {
        try {
            String userName = TokenService.validarToken(token);

            return usuarioService.getUsuario(userName);
        } catch (Exception e) {
            throw new NotAuthorizedException("Token inválido: " + e.getMessage());
        }
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
