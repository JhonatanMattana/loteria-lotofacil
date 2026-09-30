package br.com.loteria.repository.local;

import java.util.Optional;

import br.com.loteria.entidade.SorteioLotofacil;

public interface SorteioLotofacilRepositoryLocal {
	Optional<SorteioLotofacil> buscarSorteioPorNumero(Short numero);
	Optional<SorteioLotofacil> buscarUltimoSorteioRealizado();
}