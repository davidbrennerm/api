package forum.hub.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import forum.hub.api.domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem,
                                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") LocalDateTime dataDeCriacao, String autor, String curso, String ativo) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataDeCriacao(), String.valueOf(topico.getAutor().getNome()), String.valueOf(topico.getCurso().getNome()), String.valueOf(topico.getAtivo()));
    }
}
