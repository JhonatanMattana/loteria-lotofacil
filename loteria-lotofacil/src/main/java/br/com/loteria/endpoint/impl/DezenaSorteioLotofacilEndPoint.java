package br.com.loteria.endpoint.impl;

import javax.ejb.EJB;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.loteria.dto.DezenaSorteioLotofacilDTO;
import br.com.loteria.endpoint.IDezenaSorteioLotofacilEndPoint;
import br.com.loteria.enums.FiltroGanhadoresPremioPrincipalEnum;
import br.com.loteria.enums.OrdenacaoDezenaLotofacilEnum;
import br.com.loteria.service.local.DezenaSorteioLotofacilServiceLocal;

import io.swagger.annotations.Api;

@Path("/dezena-sorteio-lotofacil")
@Api(value = "Dezena Sorteio Lotofacil", tags = {"Dezena Sorteio Lotofacil"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DezenaSorteioLotofacilEndPoint implements IDezenaSorteioLotofacilEndPoint {
	
	@EJB
	private DezenaSorteioLotofacilServiceLocal service;

	@Override
	public Response ordemSorteio(
			@QueryParam("numeroConcurso") Short numeroConcurso,
			@QueryParam("numeroConcursoDe") Short numeroConcursoDe,
			@QueryParam("numeroConcursoAte") Short numeroConcursoAte,
			@QueryParam("teveGanhadorPremioPrincipal") Boolean teveGanhadorPremioPrincipal,
			@QueryParam("filtroGanhadoresPremioPrincipal") FiltroGanhadoresPremioPrincipalEnum filtroGanhadoresPremioPrincipal) {
		return Response.ok(buscar(numeroConcurso, numeroConcursoDe, numeroConcursoAte, teveGanhadorPremioPrincipal,
				filtroGanhadoresPremioPrincipal,
				OrdenacaoDezenaLotofacilEnum.ORDEM_SORTEIO)).build();
	}

	@Override
	public Response ordenadasAscendentes(
			@QueryParam("numeroConcurso") Short numeroConcurso,
			@QueryParam("numeroConcursoDe") Short numeroConcursoDe,
			@QueryParam("numeroConcursoAte") Short numeroConcursoAte,
			@QueryParam("teveGanhadorPremioPrincipal") Boolean teveGanhadorPremioPrincipal,
			@QueryParam("filtroGanhadoresPremioPrincipal") FiltroGanhadoresPremioPrincipalEnum filtroGanhadoresPremioPrincipal) {
		return Response.ok(buscar(numeroConcurso, numeroConcursoDe, numeroConcursoAte, teveGanhadorPremioPrincipal,
				filtroGanhadoresPremioPrincipal,
				OrdenacaoDezenaLotofacilEnum.ASCENDENTE)).build();
	}

	@Override
	public Response ordenadasDescendentes(
			@QueryParam("numeroConcurso") Short numeroConcurso,
			@QueryParam("numeroConcursoDe") Short numeroConcursoDe,
			@QueryParam("numeroConcursoAte") Short numeroConcursoAte,
			@QueryParam("teveGanhadorPremioPrincipal") Boolean teveGanhadorPremioPrincipal,
			@QueryParam("filtroGanhadoresPremioPrincipal") FiltroGanhadoresPremioPrincipalEnum filtroGanhadoresPremioPrincipal) {
		return Response.ok(buscar(numeroConcurso, numeroConcursoDe, numeroConcursoAte, teveGanhadorPremioPrincipal,
				filtroGanhadoresPremioPrincipal,
				OrdenacaoDezenaLotofacilEnum.DESCENDENTE)).build();
	}

	private List<DezenaSorteioLotofacilDTO> buscar(Short numeroConcurso, Short numeroConcursoDe,
			Short numeroConcursoAte, Boolean teveGanhadorPremioPrincipal,
			FiltroGanhadoresPremioPrincipalEnum filtroGanhadoresPremioPrincipal,
			OrdenacaoDezenaLotofacilEnum ordenacao) {
		return service.buscarDezenas(numeroConcurso, numeroConcursoDe, numeroConcursoAte,
				teveGanhadorPremioPrincipal, filtroGanhadoresPremioPrincipal, ordenacao);
	}

}