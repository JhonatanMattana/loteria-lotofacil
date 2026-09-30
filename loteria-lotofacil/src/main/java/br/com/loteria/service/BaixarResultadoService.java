package br.com.loteria.service;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.loteria.dto.BaixarResultadoDTO;
import br.com.loteria.dto.VerificarSorteioSiteDTO;
import br.com.loteria.entidade.ConcursoLotofacil;
import br.com.loteria.entidade.DezenaSorteioLotofacil;
import br.com.loteria.entidade.LoteriaClient;
import br.com.loteria.entidade.SorteioLotofacil;
import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.exception.LoteriaException;
import br.com.loteria.repository.local.BaixarResultadoRepositoryLocal;
import br.com.loteria.service.local.BaixarResultadoServiceLocal;
import br.com.loteria.service.local.ConcursoServiceLocal;
import br.com.loteria.util.DateUtil;

@Stateless
@Local(BaixarResultadoServiceLocal.class)
public class BaixarResultadoService implements BaixarResultadoServiceLocal {
	
	@EJB
	private BaixarResultadoRepositoryLocal baixarResultadoRepository;
	
	@EJB
	private LoteriaClient lotofacilClient;
	
	@EJB
	private ConcursoServiceLocal concursoService;
	
	@Override
	public BaixarResultadoDTO porNumeroConcurso(Integer numero, String modalidade) {
		return lotofacilClient.buscarResultado(numero, modalidade);
	}

	@Override
	public VerificarSorteioSiteDTO verificarSorteioSite(Integer numeroConcurso, String modalidade) {
		try {
			lotofacilClient.verificarSorteioSite(numeroConcurso, modalidade);
			String message = String.format("Sorteio da %s, número %d, está disponível!", modalidade, numeroConcurso);
			return new VerificarSorteioSiteDTO(message);
		} catch (Exception e) {
			String message = String.format("Sorteio da %s, número %d, não está disponível!", modalidade, numeroConcurso);
			return new VerificarSorteioSiteDTO(message);
		}
	}
	
	@Override
	public void salvarResultado(BaixarResultadoDTO dto) {
		validarSalvarResultado(dto);
		
        ConcursoLotofacil concurso = montarObjetoParaSalvar(dto);

        baixarResultadoRepository.salvarConcurso(concurso);
    }
	
	private void validarSalvarResultado(BaixarResultadoDTO dto) {
		Optional<ConcursoLotofacil> concursoOptional = concursoService.buscarPorNumeroConcurso(dto.getNumero());
		
		if (concursoOptional.isPresent()) {
			String message = String.format("Concurso N° \"%d\" já esta salvo.", dto.getNumero());
			throw new LoteriaException(message, HttpErrorStatusEnum.INTERNAL_ERROR);
		}
		
	}

	private ConcursoLotofacil montarObjetoParaSalvar(BaixarResultadoDTO dto) {
		ConcursoLotofacil concurso = new ConcursoLotofacil();

        concurso.setNumero(dto.getNumero());
        concurso.setDataProximoConcurso(DateUtil.converterParaLocalDate(dto.getDataProximoConcurso()));
        concurso.setNumeroConcursoAnterior(dto.getNumeroConcursoAnterior());
        concurso.setNumeroConcursoProximo(dto.getNumeroConcursoProximo());
        concurso.setModalidade(dto.getModalidade());
        concurso.setValorAcumuladoConcursoEspecial(dto.getValorAcumuladoConcursoEspecial());
        concurso.setValorAcumuladoProximoConcurso(dto.getValorAcumuladoProximoConcurso());
        concurso.setValorEstimadoProximoConcurso(dto.getValorEstimadoProximoConcurso());
        concurso.setValorArrecadado(dto.getValorArrecadado());
        concurso.setAcumulado(dto.isAcumulado());

        SorteioLotofacil sorteio = new SorteioLotofacil();

        sorteio.setNumero(dto.getNumero());
        sorteio.setLocalSorteio(dto.getLocalSorteio());
        sorteio.setNomeMunicipioUFSorteio(dto.getNomeMunicipioUFSorteio());
        sorteio.setDataApuracao(DateUtil.converterParaLocalDate(dto.getDataApuracao()));
        sorteio.setModalidade(dto.getModalidade());

        concurso.setSorteioLotofacil(sorteio);
        sorteio.setConcursoLotofacil(concurso);

        if (dto.getDezenasSorteadasOrdemSorteio() != null) {
        	Byte ordem = 1;
            for (Byte dezena : dto.getDezenasSorteadasOrdemSorteio()) {

                DezenaSorteioLotofacil dezenaSorteioLotofacil = new DezenaSorteioLotofacil();

                dezenaSorteioLotofacil.setDezena(dezena);
                dezenaSorteioLotofacil.setOrdem(ordem);
                dezenaSorteioLotofacil.setDataSorteio(DateUtil.converterParaLocalDate(dto.getDataApuracao()));

                sorteio.addDezenasLotofacil(dezenaSorteioLotofacil);
                
                ordem++;
            }
        }
		return concurso;
	}

}