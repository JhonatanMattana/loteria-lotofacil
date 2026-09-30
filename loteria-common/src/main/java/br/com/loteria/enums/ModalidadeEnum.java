package br.com.loteria.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ModalidadeEnum {
	LOTOFACIL(1, "LotoFacil");
	
	private Integer codigo;
	
	private String descricao;

	private ModalidadeEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static String getDescricaoLowerCase(String modalidade) {
		return Arrays.stream(values())
				.filter(m -> m.descricao.equalsIgnoreCase(modalidade))
				.map(m -> m.getDescricao().toLowerCase())
				.findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException("Modalidade inválida: " + modalidade)
				);
	}
}