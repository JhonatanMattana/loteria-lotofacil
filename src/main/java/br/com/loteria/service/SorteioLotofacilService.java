package br.com.loteria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.loteria.dto.DezenaSorteioLotofacilDTO;
import br.com.loteria.dto.SorteioLotofacilDTO;
import br.com.loteria.entidade.DezenaSorteioLotofacil;
import br.com.loteria.entidade.SorteioLotofacil;
import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.exception.LoteriaException;
import br.com.loteria.repository.local.SorteioLotofacilRepositoryLocal;
import br.com.loteria.service.local.SorteioLotofacilServiceLocal;

@Stateless
@Local(SorteioLotofacilServiceLocal.class)
public class SorteioLotofacilService implements SorteioLotofacilServiceLocal {
	
	@EJB
	private SorteioLotofacilRepositoryLocal sorteioLotofacilRepository;

	@Override
	public SorteioLotofacilDTO buscarSorteioPorNumero(Short numero) {
		Optional<SorteioLotofacil> sorteioOptional = sorteioLotofacilRepository.buscarSorteioPorNumero(numero);
		if (sorteioOptional.isEmpty()) {
			throw new LoteriaException("Não foi encontrato Sorteio com Nº " + numero, HttpErrorStatusEnum.NOT_FOUND);
		}
		return converterEntidadeParaDTO(sorteioOptional.get());
	}

	@Override
	public SorteioLotofacilDTO buscarUltimoSorteioRealizado() {
		Optional<SorteioLotofacil> ultimoSorteioOptional = sorteioLotofacilRepository.buscarUltimoSorteioRealizado();
		if (ultimoSorteioOptional.isEmpty()) {
			throw new LoteriaException("Foi localizado nenhum Sorteio cadastrado no Sistema.", HttpErrorStatusEnum.NOT_FOUND);
		}
		return converterEntidadeParaDTO(ultimoSorteioOptional.get());
	}
	
	private SorteioLotofacilDTO converterEntidadeParaDTO(SorteioLotofacil entidade) {
		SorteioLotofacilDTO dto = new SorteioLotofacilDTO();
		
		dto.setNumero(entidade.getNumero());
		dto.setLocalSorteio(entidade.getLocalSorteio());
		dto.setNomeMunicipioUFSorteio(entidade.getNomeMunicipioUFSorteio());
		dto.setDataApuracao(entidade.getDataApuracao());
		dto.setModalidade(entidade.getModalidade());
		
		List<DezenaSorteioLotofacilDTO> listaDezenaSorteioDto = new ArrayList<>();
		for (DezenaSorteioLotofacil dezena : entidade.getDezenasLotofacil()) {
			DezenaSorteioLotofacilDTO dezenaDto = new DezenaSorteioLotofacilDTO();
			
			dezenaDto.setDezena(dezena.getDezena());
			dezenaDto.setOrdem(dezena.getOrdem());
			dezenaDto.setDataSorteio(dezena.getDataSorteio());
			
			listaDezenaSorteioDto.add(dezenaDto);
		}
		
		dto.setDezenasLotofacil(listaDezenaSorteioDto);
		
		return dto;
	}
}