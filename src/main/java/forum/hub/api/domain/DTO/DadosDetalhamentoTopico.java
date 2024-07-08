package forum.hub.api.domain.DTO;

import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, LocalDateTime dataDeCriacao, Usuario autor, String curso) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataDeCriacao(), topico.getAutor(), String.valueOf(topico.getCurso()));
    }
}
