package br.com.loteria.service;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.loteria.entidade.ConcursoLotofacil;
import br.com.loteria.repository.local.ConcursoRepositoryLocal;
import br.com.loteria.service.local.ConcursoServiceLocal;


@Stateless
@Local(ConcursoServiceLocal.class)
public class ConcursoService implements ConcursoServiceLocal {

	@EJB
	private ConcursoRepositoryLocal concursoRepository;
	
	@Override
	public boolean isConcursoSalvo(Short numeroConcurso) {
		return concursoRepository.isConcursoSalvo(numeroConcurso);
	}

	@Override
	public Optional<ConcursoLotofacil> buscarPorNumeroConcurso(Short numeroConcurso) {		
		return Optional.ofNullable(concursoRepository.buscarPorNumeroConcurso(numeroConcurso));
	}

}