package forum.hub.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaTopico(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        @Valid
        String autor,
        String curso,
        String categoria) {
}
