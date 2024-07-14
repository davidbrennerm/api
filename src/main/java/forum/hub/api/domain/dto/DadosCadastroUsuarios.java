package forum.hub.api.domain.dto;

import forum.hub.api.domain.usuario.Perfil;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuarios(
        @NotNull
        Usuario nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @NotBlank
        Perfil perfil) {
}
