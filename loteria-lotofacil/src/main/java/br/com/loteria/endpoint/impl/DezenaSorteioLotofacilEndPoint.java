package br.com.loteria.endpoint.impl;

import javax.ws.rs.Consumes;
import javax.ejb.EJB;
import java.util.List;

import br.com.loteria.dto.DezenaSorteioLotofacilDTO;
import br.com.loteria.enums.OrdenacaoDezenaLotofacilEnum;
import br.com.loteria.service.local.DezenaSorteioLotofacilServiceLocal;
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
	
	@EJB
	private DezenaSorteioLotofacilServiceLocal service;

	@Override
	public Response ordemSorteio(Short numeroConcurso, Short numeroConcursoDe, Short numeroConcursoAte) {
		return Response.ok(buscar(numeroConcurso, numeroConcursoDe, numeroConcursoAte,
				OrdenacaoDezenaLotofacilEnum.ORDEM_SORTEIO)).build();
	}

	@Override
	public Response ordenadasAscendentes(Short numeroConcurso, Short numeroConcursoDe, Short numeroConcursoAte) {
		return Response.ok(buscar(numeroConcurso, numeroConcursoDe, numeroConcursoAte,
				OrdenacaoDezenaLotofacilEnum.ASCENDENTE)).build();
	}

	@Override
	public Response ordenadasDescendentes(Short numeroConcurso, Short numeroConcursoDe, Short numeroConcursoAte) {
		return Response.ok(buscar(numeroConcurso, numeroConcursoDe, numeroConcursoAte,
				OrdenacaoDezenaLotofacilEnum.DESCENDENTE)).build();
	}

	private List<DezenaSorteioLotofacilDTO> buscar(Short numeroConcurso, Short numeroConcursoDe,
			Short numeroConcursoAte, OrdenacaoDezenaLotofacilEnum ordenacao) {
		return service.buscarDezenas(numeroConcurso, numeroConcursoDe, numeroConcursoAte, ordenacao);
	}

}