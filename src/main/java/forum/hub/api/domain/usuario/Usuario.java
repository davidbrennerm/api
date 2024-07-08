package forum.hub.api.domain.usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import forum.hub.api.domain.DTO.DadosCadastroUsuarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    @JsonCreator
    public Usuario(@JsonProperty("autor") String nome, @JsonProperty("email") String email,
                   @JsonProperty("senha") String senha, @JsonProperty("perfil") Perfil perfil) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }


    private Usuario(DadosCadastroUsuarios dados) {
        this.nome = String.valueOf(dados.nome());
        this.email = dados.email();
        this.senha = dados.senha();
        this.perfil = dados.perfil();
    }

    @Override
    public String toString() {
        return "{" +
                " autor='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", perfil=" + perfil +
                "}";
    }
}
