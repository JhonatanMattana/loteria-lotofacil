package br.com.loteria.security.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "token")
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }
    
}
