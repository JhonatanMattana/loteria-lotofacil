package br.com.loteria.repository.local;

import br.com.loteria.entidade.ConcursoLotofacil;

public interface ConcursoRepositoryLocal {
	boolean isConcursoSalvo(Short numeroConcurso);
	ConcursoLotofacil buscarPorNumeroConcurso(Short numeroConcurso);
}