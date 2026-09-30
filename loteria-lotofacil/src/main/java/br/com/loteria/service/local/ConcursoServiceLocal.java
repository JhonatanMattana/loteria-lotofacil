package br.com.loteria.service.local;

import java.util.Optional;

import br.com.loteria.entidade.ConcursoLotofacil;

public interface ConcursoServiceLocal {
	boolean isConcursoSalvo(Short numeroConcurso);
	Optional<ConcursoLotofacil> buscarPorNumeroConcurso(Short numeroConcurso);
}