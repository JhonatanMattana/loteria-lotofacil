package br.com.loteria.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.loteria.dto.DezenaSorteioLotofacilDTO;
import br.com.loteria.entidade.DezenaSorteioLotofacil;
import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.enums.OrdenacaoDezenaLotofacilEnum;
import br.com.loteria.exception.LoteriaException;
import br.com.loteria.repository.local.DezenaSorteioLotofacilRepositoryLocal;
import br.com.loteria.service.local.DezenaSorteioLotofacilServiceLocal;

@Stateless
@Local(DezenaSorteioLotofacilServiceLocal.class)
public class DezenaSorteioLotofacilService implements DezenaSorteioLotofacilServiceLocal {

	@EJB
	private DezenaSorteioLotofacilRepositoryLocal repository;

	@Override
	public List<DezenaSorteioLotofacilDTO> buscarDezenas(Short numeroConcurso, Short numeroConcursoDe,
			Short numeroConcursoAte, OrdenacaoDezenaLotofacilEnum ordenacao) {
		validarFiltros(numeroConcurso, numeroConcursoDe, numeroConcursoAte);
		return repository.buscarDezenas(numeroConcurso, numeroConcursoDe, numeroConcursoAte, ordenacao)
				.stream().map(this::converterParaDTO).collect(Collectors.toList());
	}

	private void validarFiltros(Short numeroConcurso, Short numeroConcursoDe, Short numeroConcursoAte) {
		if (numeroConcurso == null && numeroConcursoDe == null && numeroConcursoAte == null) {
			throw new LoteriaException("Informe um concurso ou uma faixa de concursos.", HttpErrorStatusEnum.BAD_REQUEST);
		}
		if (numeroConcurso != null && (numeroConcursoDe != null || numeroConcursoAte != null)) {
			throw new LoteriaException("Use o concurso individual ou a faixa, não ambos.", HttpErrorStatusEnum.BAD_REQUEST);
		}
		if ((numeroConcursoDe == null) != (numeroConcursoAte == null)) {
			throw new LoteriaException("Informe os concursos inicial e final da faixa.", HttpErrorStatusEnum.BAD_REQUEST);
		}
		if (numeroConcurso != null && numeroConcurso < 1) {
			throw new LoteriaException("O número do concurso deve ser positivo.", HttpErrorStatusEnum.BAD_REQUEST);
		}
		if (numeroConcursoDe != null && (numeroConcursoDe < 1 || numeroConcursoAte < numeroConcursoDe)) {
			throw new LoteriaException("A faixa de concursos informada é inválida.", HttpErrorStatusEnum.BAD_REQUEST);
		}
	}

	private DezenaSorteioLotofacilDTO converterParaDTO(DezenaSorteioLotofacil entidade) {
		DezenaSorteioLotofacilDTO dto = new DezenaSorteioLotofacilDTO();
		dto.setId(entidade.getId());
		dto.setDezena(entidade.getDezena());
		dto.setOrdem(entidade.getOrdem());
		dto.setDataSorteio(entidade.getDataSorteio());
		dto.setNumeroConcurso(entidade.getSorteioLotofacil().getConcursoLotofacil().getNumero());
		return dto;
	}
}