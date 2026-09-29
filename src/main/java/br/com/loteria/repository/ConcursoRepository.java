package br.com.loteria.repository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.loteria.entidade.ConcursoLotofacil;
import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.exception.LoteriaException;
import br.com.loteria.repository.local.ConcursoRepositoryLocal;

@Stateless
@Local(ConcursoRepositoryLocal.class)
public class ConcursoRepository implements ConcursoRepositoryLocal {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public boolean isConcursoSalvo(Short numeroConcurso) {
		Long quantidade = entityManager.createQuery(
	                " SELECT COUNT(c) " +
	                " FROM ConcursoLotofacil c " +
	                " WHERE c.numero = :numeroConcurso",
                Long.class)
                .setParameter("numeroConcurso", numeroConcurso)
                .getSingleResult();

        return quantidade > 0;
	}

	@Override
	public ConcursoLotofacil buscarPorNumeroConcurso(Short numeroConcurso) {
		try {

            return entityManager.createQuery(
	                    " SELECT c " +
	                    " FROM ConcursoLotofacil c " +
	                    " WHERE c.numero= :numeroConcurso",
                    ConcursoLotofacil.class)
                    .setParameter("numeroConcurso", numeroConcurso)
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new LoteriaException(e.getMessage(), HttpErrorStatusEnum.NOT_FOUND);
        }
	}
	
}