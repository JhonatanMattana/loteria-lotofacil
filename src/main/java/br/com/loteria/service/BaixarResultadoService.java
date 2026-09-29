package br.com.loteria.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.loteria.dto.BaixarResultadoDTO;
import br.com.loteria.dto.VerificarSorteioSiteDTO;
import br.com.loteria.entidade.ConcursoLotofacil;
import br.com.loteria.entidade.DezenaSorteioLotofacil;
import br.com.loteria.entidade.SorteioLotofacil;
import br.com.loteria.repository.local.BaixarResultadoRepositoryLocal;
import br.com.loteria.util.client.DateUtil;
import br.com.loteria.util.client.LoteriaClient;

@Stateless
public class BaixarResultadoService {
	
	@EJB
	private BaixarResultadoRepositoryLocal baixarResultadoRepository;
	
	@EJB
	private LoteriaClient lotofacilClient;
	
	public BaixarResultadoDTO porNumeroConcurso(Integer numero, String modalidade) {
		return lotofacilClient.buscarResultado(numero, modalidade);
	}

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
	
	public void salvarResultado(BaixarResultadoDTO dto) {
        ConcursoLotofacil concurso = new ConcursoLotofacil();

        concurso.setNumero(dto.getNumero());
        concurso.setDataProximoConcurso(DateUtil.converterParaLocalDate(dto.getDataProximoConcurso()));
        concurso.setNumeroConcursoAnterior(dto.getNumeroConcursoAnterior());
        concurso.setNumeroConcursoProximo(dto.getNumeroConcursoProximo());
        concurso.setModalidade(dto.getModalidade());
        concurso.setValorArrecadado(dto.getValorArrecadado());

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

        baixarResultadoRepository.salvarConcurso(concurso);
    }

}