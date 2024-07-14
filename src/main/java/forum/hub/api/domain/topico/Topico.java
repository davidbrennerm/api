package forum.hub.api.domain.topico;

import forum.hub.api.domain.dto.DadosCadastroTopicos;
import forum.hub.api.domain.curso.Categoria;
import forum.hub.api.domain.curso.Curso;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private LocalDateTime dataDeCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private Boolean ativo;

    public Topico(DadosCadastroTopicos dados, Usuario autor, Curso curso) {
        this.ativo = true;
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataDeCriacao = LocalDateTime.now();
        this.autor = autor;
        this.curso = curso;
        this.categoria = dados.categoria();
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
