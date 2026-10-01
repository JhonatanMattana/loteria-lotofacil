package br.com.loteria.repository;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.loteria.entidade.DezenaSorteioLotofacil;
import br.com.loteria.enums.OrdenacaoDezenaLotofacilEnum;
import br.com.loteria.repository.local.DezenaSorteioLotofacilRepositoryLocal;

@Stateless
@Local(DezenaSorteioLotofacilRepositoryLocal.class)
public class DezenaSorteioLotofacilRepository implements DezenaSorteioLotofacilRepositoryLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DezenaSorteioLotofacil> buscarDezenas(Short numeroConcurso, Short numeroConcursoDe,
			Short numeroConcursoAte, OrdenacaoDezenaLotofacilEnum ordenacao) {
		StringBuilder jpql = new StringBuilder(
				" SELECT d FROM DezenaSorteioLotofacil d " +
				" JOIN FETCH d.sorteioLotofacil s " +
				" JOIN FETCH s.concursoLotofacil c " +
				" WHERE 1 = 1 ");

		if (numeroConcurso != null) {
			jpql.append("AND c.numero = :numeroConcurso ");
		} else if (numeroConcursoDe != null) {
			jpql.append("AND c.numero BETWEEN :numeroConcursoDe AND :numeroConcursoAte ");
		}

		jpql.append("ORDER BY ").append(ordenacao.getOrderBy());

		TypedQuery<DezenaSorteioLotofacil> query = entityManager.createQuery(jpql.toString(), DezenaSorteioLotofacil.class);
		
        if (numeroConcurso != null) {
			query.setParameter("numeroConcurso", numeroConcurso);
		} else if (numeroConcursoDe != null) {
			query.setParameter("numeroConcursoDe", numeroConcursoDe);
			query.setParameter("numeroConcursoAte", numeroConcursoAte);
		}

		return query.getResultList();
	}
}