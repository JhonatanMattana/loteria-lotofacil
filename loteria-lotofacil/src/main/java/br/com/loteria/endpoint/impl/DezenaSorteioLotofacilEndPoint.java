package br.com.loteria.endpoint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.endpoint.IDezenaSorteioLotofacilEndPoint;
import io.swagger.annotations.Api;

@Path("/dezena-sorteio-lotofacil")
@Api(value = "Dezena Sorteio Lotofacil", tags = {"Dezena Sorteio Lotofacil"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DezenaSorteioLotofacilEndPoint implements IDezenaSorteioLotofacilEndPoint {

	@Override
	public Response ordemSorteio() {
		return null;
	}

	@Override
	public Response ordenadasAscendentes() {
		return null;
	}

	@Override
	public Response ordenadasDescendentes() {
		return null;
	}

}