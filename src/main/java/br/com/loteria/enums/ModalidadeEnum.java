package br.com.loteria.enums;

import lombok.Getter;

@Getter
public enum ModalidadeEnum {
	LOTOFACIL(1, "LotoFacil");
	
	private Integer codigo;
	
	private String descricao;

	private ModalidadeEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
