package forum.hub.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import forum.hub.api.domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosListagemTopicos(Long id, String titulo, String mensagem, String autor, String curso,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDateTime dataDeCriacao, Boolean ativo) {

    public DadosListagemTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNome(), topico.getCurso().getNome(), topico.getDataDeCriacao(), topico.getAtivo());
    }

}
