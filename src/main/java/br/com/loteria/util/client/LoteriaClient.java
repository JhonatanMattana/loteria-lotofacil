package br.com.loteria.util.client;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.dto.BaixarResultadoDTO;
import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.enums.ModalidadeEnum;
import br.com.loteria.exception.LoteriaException;

@Stateless
public class LoteriaClient {

    // Exemplo de URL válida: portaldeloterias/api/lotofacil/3370
    private static final String URL_BASE = "https://servicebus2.caixa.gov.br/portaldeloterias/api";

    private Client client;

    public BaixarResultadoDTO buscarResultado(Integer numeroConcurso, String modalidade) {

        Response response = request(numeroConcurso, modalidade);

        if (response.getStatus() != 200) {
            throw new LoteriaException("Erro ao chamar API da Caixa", HttpErrorStatusEnum.INTERNAL_ERROR);
        }

        BaixarResultadoDTO resultado = response.readEntity(BaixarResultadoDTO.class);
        resultado.setModalidade(ModalidadeEnum.valueOf(modalidade.toUpperCase()));

        response.close();
        getClient().close();

        return resultado;
    }

    public Boolean verificarSorteioSite(Integer numeroConcurso, String modalidade) {
        Response response = request(numeroConcurso, modalidade);

        if (response.getStatus() != 200) {
            throw new LoteriaException("Erro ao chamar API da Caixa", HttpErrorStatusEnum.INTERNAL_ERROR);
        }

        boolean resultado = response.hasEntity();

        response.close();
        getClient().close();

        return resultado;
    }

    private Response request(Integer numeroConcurso, String modalidade) {
        Client client = getClient();

        final String URL = getRrl(modalidade);

        WebTarget target = client
                .target(URL)
                .path(String.valueOf(numeroConcurso));

        return target
                .request(MediaType.APPLICATION_JSON)
                .get();
    }

    private String getRrl(String modalidade) {
        return URL_BASE + "/" + getModalidadeLowerCase(modalidade);
    }

    private Client getClient() {
        if (client == null) {
            client = ClientBuilder.newClient();
        }
        return client;
    }

    private String getModalidadeLowerCase(String modalidade) {
        return ModalidadeEnum.getDescricaoLowerCase(modalidade);
    }

}