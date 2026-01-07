package br.com.loteria.security.repository;

import br.com.loteria.security.entidade.Usuario;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
@LocalBean
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<Usuario> findByUsuario(String usuario) {
        String queryUsuario = "SELECT u FROM Usuario u WHERE u.usuario = :usuario";

        try {
            Usuario usuarioConsultado = em.createQuery(queryUsuario, Usuario.class)
                    .setParameter("usuario", usuario)
                    .getSingleResult();

            return Optional.of(usuarioConsultado);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            em.persist(usuario);
            return usuario;
        } else {
            return em.merge(usuario);
        }
    }

    public Optional<Usuario> findById(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        return Optional.ofNullable(usuario);
    }

    public boolean existsByUsuario(String usuario) {
        String queryCountUsuario = "SELECT COUNT(u) FROM Usuario u WHERE u.usuario = :usuario";

        Long count = em.createQuery(queryCountUsuario, Long.class)
                .setParameter("usuario", usuario)
                .getSingleResult();

        return count > 0;
    }
}
