package forum.hub.api.domain.DTO;

import forum.hub.api.domain.curso.Categoria;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroTopicos(
        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        Usuario autor,

        @NotBlank
        String curso,

        @NotNull
        Categoria categoria) {
}
