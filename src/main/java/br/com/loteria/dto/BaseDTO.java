package br.com.loteria.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class BaseDTO {

	private LocalDate dateCreate;
	
	private LocalDate lastUpdate;
	
}
