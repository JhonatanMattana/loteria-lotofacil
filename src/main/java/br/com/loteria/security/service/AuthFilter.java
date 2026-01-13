package br.com.loteria.security.service;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import br.com.loteria.security.entidade.Usuario;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = Logger.getLogger(AuthFilter.class.getName());

    @EJB
    private AuthService authService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        	LOGGER.log(Level.WARNING, "Token ausente");
            abort(requestContext, "Token ausente");
            return;
        }

        String token = authHeader.substring("Bearer ".length());

        try {
            // Valida token e obtém usuário
            Usuario usuario = authService.validarToken(token);

            // Cria SecurityContext customizado
            SecurityContext securityContext = new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> usuario.getUsuario();
                }

                @Override
                public boolean isUserInRole(String role) {
                    // TODO: Implementar sistema de roles
                    return true;
                }

                @Override
                public boolean isSecure() {
                    return requestContext.getSecurityContext().isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return "Bearer";
                }
            };

            // Define o SecurityContext
            requestContext.setSecurityContext(securityContext);

            // Salva usuário no contexto para uso posterior
            requestContext.setProperty("usuario", usuario);

            LOGGER.log(Level.FINE, "Usuário autenticado: {0}", usuario.getUsuario());

        } catch (NotAuthorizedException e) {
            LOGGER.log(Level.WARNING, "Token inválido: {0}", e.getMessage());
            abort(requestContext, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao validar token", e);
            abort(requestContext, "Erro ao validar token");
        }
    }

    private void abort(ContainerRequestContext requestContext, String mensagem) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"erro\": \"" + mensagem + "\"}")
                        .build()
        );
    }

}
