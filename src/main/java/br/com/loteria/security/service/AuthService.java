package br.com.loteria.security.service;

import br.com.loteria.security.dto.LoginDTO;
import br.com.loteria.security.entidade.Usuario;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AuthService {

    @EJB
    private UsuarioService usuarioService;

    public String login(LoginDTO dto) {
        String token = null;

        Usuario usuarioConsultado = usuarioService.getFindUsuario(dto);
        if (usuarioConsultado != null) {
            token = TokenService.gerarToken(dto.getUsuario());
        }

        return token;
    }

}
