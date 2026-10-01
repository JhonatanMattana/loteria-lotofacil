package br.com.loteria.enums;

public enum OrdenacaoDezenaLotofacilEnum {
	ORDEM_SORTEIO("c.numero ASC, d.ordem ASC"),
	ASCENDENTE("c.numero ASC, d.dezena ASC"),
	DESCENDENTE("c.numero ASC, d.dezena DESC");

	private final String orderBy;

	OrdenacaoDezenaLotofacilEnum(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderBy() {
		return orderBy;
	}
}