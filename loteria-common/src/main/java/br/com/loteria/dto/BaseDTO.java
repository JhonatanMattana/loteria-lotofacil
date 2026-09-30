package br.com.loteria.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate dateCreate;
	
	private LocalDate lastUpdate;
	
}
