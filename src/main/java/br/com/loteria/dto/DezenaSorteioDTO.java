package br.com.loteria.dto;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class DezenaSorteioDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Byte dezena;
	
	private Byte ordem;

}
