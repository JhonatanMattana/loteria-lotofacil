package br.com.loteria.security.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
