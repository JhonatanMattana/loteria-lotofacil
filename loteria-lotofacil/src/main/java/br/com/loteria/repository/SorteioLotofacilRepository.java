package br.com.loteria.repository;

import java.util.Optional;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.loteria.entidade.SorteioLotofacil;
import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.exception.LoteriaException;
import br.com.loteria.repository.local.SorteioLotofacilRepositoryLocal;

@Stateless
@Local(SorteioLotofacilRepositoryLocal.class)
public class SorteioLotofacilRepository implements SorteioLotofacilRepositoryLocal {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public Optional<SorteioLotofacil> buscarSorteioPorNumero(Short numero) {
		try {
			SorteioLotofacil sorteioLotofacil = entityManager.createQuery(
	                    " SELECT s FROM SorteioLotofacil s " +
	                    " WHERE s.numero = :numero",
	                    SorteioLotofacil.class)
                    .setParameter("numero", numero)
                    .getSingleResult();

			return Optional.ofNullable(sorteioLotofacil);
        } catch (Exception e) {
            throw new LoteriaException(e.getMessage(), HttpErrorStatusEnum.NOT_FOUND);
        }
	}

	@Override
	public Optional<SorteioLotofacil> buscarUltimoSorteioRealizado() {
		try {
            return entityManager.createQuery(
	                    " SELECT s FROM SorteioLotofacil s " +
	                    " ORDER BY s.numero DESC",
	                    SorteioLotofacil.class)
            .setMaxResults(1)
            .getResultStream()
            .findFirst();
        } catch (Exception e) {
            throw new LoteriaException(e.getMessage(), HttpErrorStatusEnum.NOT_FOUND);
        }
	}

}