package forum.hub.api.domain.topico;

import forum.hub.api.domain.DTO.DadosCadastroTopicos;
import forum.hub.api.domain.DTO.DadosDetalhamentoTopico;
import forum.hub.api.domain.DTO.DadosListagemTopicos;
import forum.hub.api.domain.curso.Categoria;
import forum.hub.api.domain.curso.Curso;
import forum.hub.api.domain.curso.CursoRepository;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com id: " + id));
        return new DadosDetalhamentoTopico(topico);
    }

}
