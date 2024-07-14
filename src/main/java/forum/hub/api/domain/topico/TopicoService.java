package forum.hub.api.domain.topico;

import forum.hub.api.domain.dto.DadosAtualizaTopico;
import forum.hub.api.domain.dto.DadosCadastroTopicos;
import forum.hub.api.domain.dto.DadosDetalhamentoTopico;
import forum.hub.api.domain.dto.DadosListagemTopicos;
import forum.hub.api.domain.curso.Categoria;
import forum.hub.api.domain.curso.Curso;
import forum.hub.api.domain.curso.CursoRepository;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico criarTopico(DadosCadastroTopicos dados) {
        Usuario autor = dados.autor();
        usuarioRepository.save(autor);

        Curso curso = cursoRepository.findByNome(dados.curso());
        if (curso == null) {
            curso = new Curso(dados.curso(), Categoria.valueOf(String.valueOf(dados.categoria())));
            cursoRepository.save(curso);
        }


        Topico topico = new Topico(dados, autor, curso);
        return topicoRepository.save(topico);
    }

    public Page<DadosListagemTopicos> listarTopicos(Pageable paginacao) {
        return topicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemTopicos::new);
    }

    public DadosDetalhamentoTopico detalhamentoDoTopico(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public Topico atualizaTopico(Long id, DadosAtualizaTopico dados) {
        Topico topico= topicoRepository.getReferenceById(id);
        if (dados.titulo() != null) {
            topico.setTitulo(dados.titulo());
        }

        if (dados.mensagem() != null) {
            topico.setMensagem(dados.mensagem());
        }

        if (dados.curso() != null) {
            Curso curso = cursoRepository.findByNome(dados.curso());
            if (curso == null) {
                curso = new Curso(dados.curso(), Categoria.valueOf(String.valueOf(dados.categoria())));
                cursoRepository.save(curso);
            }
            topico.setCurso(curso);
        }

        if (dados.autor() != null) {
            Usuario autor = usuarioRepository.findByNome(dados.autor());
            if (autor != null) {
                topico.setAutor(autor);
            }
        }

        return topico;
    }

    public Topico inativaTopico(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.setAtivo(false);
        topicoRepository.save(topico);

        return topico;
    }

}
