package br.com.loteria.repository;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.loteria.entidade.DezenaSorteioLotofacil;
import br.com.loteria.enums.FiltroGanhadoresPremioPrincipalEnum;
import br.com.loteria.enums.OrdenacaoDezenaLotofacilEnum;
import br.com.loteria.repository.local.DezenaSorteioLotofacilRepositoryLocal;

@Stateless
@Local(DezenaSorteioLotofacilRepositoryLocal.class)
public class DezenaSorteioLotofacilRepository implements DezenaSorteioLotofacilRepositoryLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DezenaSorteioLotofacil> buscarDezenas(Short numeroConcurso, Short numeroConcursoDe,
			Short numeroConcursoAte, Boolean teveGanhadorPremioPrincipal,
			FiltroGanhadoresPremioPrincipalEnum filtroGanhadoresPremioPrincipal,
			OrdenacaoDezenaLotofacilEnum ordenacao) {
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
		if (teveGanhadorPremioPrincipal != null) {
			jpql.append("AND (c.teveGanhadorPremioPrincipal = :teveGanhadorPremioPrincipal ")
					.append("OR (c.teveGanhadorPremioPrincipal IS NULL AND c.acumulado = :acumuladoLegado)) ");
		}
		if (filtroGanhadoresPremioPrincipal == FiltroGanhadoresPremioPrincipalEnum.UM) {
			jpql.append("AND EXISTS (SELECT rp.id FROM ListaRateioPremioLotofacil rp ")
					.append("WHERE rp.concursoLotofacil = c AND rp.faixa = 1 AND rp.numeroDeGanhadores = 1) ");
		} else if (filtroGanhadoresPremioPrincipal == FiltroGanhadoresPremioPrincipalEnum.MAIS_DE_UM) {
			jpql.append("AND EXISTS (SELECT rp.id FROM ListaRateioPremioLotofacil rp ")
					.append("WHERE rp.concursoLotofacil = c AND rp.faixa = 1 AND rp.numeroDeGanhadores > 1) ");
		}

		jpql.append("ORDER BY ").append(ordenacao.getOrderBy());

		TypedQuery<DezenaSorteioLotofacil> query = entityManager.createQuery(jpql.toString(), DezenaSorteioLotofacil.class);
		
        if (numeroConcurso != null) {
			query.setParameter("numeroConcurso", numeroConcurso);
		} else if (numeroConcursoDe != null) {
			query.setParameter("numeroConcursoDe", numeroConcursoDe);
			query.setParameter("numeroConcursoAte", numeroConcursoAte);
		}
		if (teveGanhadorPremioPrincipal != null) {
			query.setParameter("teveGanhadorPremioPrincipal", teveGanhadorPremioPrincipal);
			query.setParameter("acumuladoLegado", !teveGanhadorPremioPrincipal);
		}

		return query.getResultList();
	}
}