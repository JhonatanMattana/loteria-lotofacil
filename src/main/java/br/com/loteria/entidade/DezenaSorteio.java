package br.com.loteria.entidade;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class DezenaSorteio extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "DEZENA", nullable = false)
	private Byte dezena;
	
	@Column(name = "ORDEM")
	private Byte ordem;
	
	@Column(name = "DATA")
	private LocalDate data;

}
