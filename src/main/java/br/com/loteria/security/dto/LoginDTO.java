package br.com.loteria.security.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String usuario;

    @NotEmpty
    private String senha;
}
