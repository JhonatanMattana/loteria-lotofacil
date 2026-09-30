package br.com.loteria.endpoint.impl;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.dto.SorteioLotofacilDTO;
import br.com.loteria.endpoint.ISorteioLotofacilEndPoint;
import br.com.loteria.service.local.SorteioLotofacilServiceLocal;
import io.swagger.annotations.Api;

@Path("/sorteio-lotofacil")
@Api(value = "Sorteio LotoFacil", tags = {"Sorteio LotoFacil"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SorteioLotofacilEndPointImpl implements ISorteioLotofacilEndPoint {

	@EJB
	private SorteioLotofacilServiceLocal sorteioLotofacilService;
	
	@Override
	public Response buscarSorteioPorNumero(Short numeroConcurso) {
		SorteioLotofacilDTO sorteioEcontrado = sorteioLotofacilService.buscarSorteioPorNumero(numeroConcurso);
		return Response.ok(sorteioEcontrado).build();
	}

	@Override
	public Response buscarUltimoSorteioRealizado() {
		SorteioLotofacilDTO ultimoSorteioRealizado = sorteioLotofacilService.buscarUltimoSorteioRealizado();
		return Response.ok(ultimoSorteioRealizado).build();
	}

}