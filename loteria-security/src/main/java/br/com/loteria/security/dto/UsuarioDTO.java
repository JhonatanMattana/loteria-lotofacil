package br.com.loteria.security.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty
    private String usuario;
	
	@NotEmpty
    private String senha;
}
