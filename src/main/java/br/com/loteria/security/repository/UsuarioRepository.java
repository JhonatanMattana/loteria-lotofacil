package br.com.loteria.security.repository;

import java.util.List;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import br.com.loteria.security.entidade.Usuario;

@Stateless
@LocalBean
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(value = TxType.NOT_SUPPORTED)
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
    
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<Usuario> findAll() {
        return em.createQuery("FROM Usuario", Usuario.class)
                 .getResultList();
    }


    @Transactional(value = TxType.REQUIRES_NEW)
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            em.persist(usuario);
            return usuario;
        } else {
            return em.merge(usuario);
        }
    }

    @Transactional(value = TxType.NOT_SUPPORTED)
    public Optional<Usuario> findById(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        return Optional.ofNullable(usuario);
    }

    @Transactional(value = TxType.NOT_SUPPORTED)
    public boolean existsByUsuario(String usuario) {
        String queryCountUsuario = "SELECT COUNT(u) FROM Usuario u WHERE u.usuario = :usuario";

        Long count = em.createQuery(queryCountUsuario, Long.class)
                .setParameter("usuario", usuario)
                .getSingleResult();

        return count > 0;
    }

    @Transactional(value = TxType.REQUIRES_NEW)
	public void deletarUsuario(Long id) {
		String queryDeletarUsuario = "DELETE FROM Usuario u WHERE u.id = :id";
		
		em.createQuery(queryDeletarUsuario)
		  .setParameter("id", id)
		  .executeUpdate();
	}
}
