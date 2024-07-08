package forum.hub.api.domain.DTO;

import forum.hub.api.domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosListagemTopicos(Long id, String titulo, String mensagem, String autor, String curso, LocalDateTime dataDeCriacao, Boolean ativo) {

    public DadosListagemTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNome(), topico.getCurso().getNome(), topico.getDataDeCriacao(), topico.getAtivo());
    }

}
