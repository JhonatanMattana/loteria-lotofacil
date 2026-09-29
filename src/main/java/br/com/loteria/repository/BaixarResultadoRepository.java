package br.com.loteria.repository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.loteria.entidade.ConcursoLotofacil;
import br.com.loteria.repository.local.BaixarResultadoRepositoryLocal;

@Stateless
@Local(BaixarResultadoRepositoryLocal.class)
public class BaixarResultadoRepository implements BaixarResultadoRepositoryLocal {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public void salvarConcurso(ConcursoLotofacil concurso) {
		entityManager.persist(concurso);
	}

}