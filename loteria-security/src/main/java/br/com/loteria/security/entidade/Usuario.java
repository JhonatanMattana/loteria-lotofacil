package br.com.loteria.security.entidade;

import br.com.loteria.entidade.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Table(name = "USUARIO", uniqueConstraints = {
		@UniqueConstraint(
		    name = "UK_USUARIO_LOGIN",
		    columnNames = {"USUARIO"}
		)
})
public class Usuario extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USUARIO", nullable = false)
    private String usuario;

    @Column(name = "SENHA", nullable = false)
    private String senha;
}
