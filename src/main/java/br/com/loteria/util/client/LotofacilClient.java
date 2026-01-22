package br.com.loteria.util.client;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.dto.ResultadoLotoFacilDTO;
import br.com.loteria.enums.HttpErrorStatusEnum;
import br.com.loteria.exception.LoteriaException;

@Stateless
public class LotofacilClient {
	
	private static final String URL_BASE = "https://servicebus2.caixa.gov.br/portaldeloterias/api/lotofacil";

    public ResultadoLotoFacilDTO buscarResultado(Integer numeroConcurso) {

        Client client = ClientBuilder.newClient();

        WebTarget target = client
                .target(URL_BASE)
                .path(String.valueOf(numeroConcurso));

        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() != 200) {
            throw new LoteriaException("Erro ao chamar API da Caixa", HttpErrorStatusEnum.INTERNAL_ERROR);
        }

        ResultadoLotoFacilDTO resultado =
                response.readEntity(ResultadoLotoFacilDTO.class);

        response.close();
        client.close();

        return resultado;
    }
}
