package forum.hub.api.controller;

import forum.hub.api.domain.DTO.DadosCadastroTopicos;
import forum.hub.api.domain.DTO.DadosListagemTopicos;
import forum.hub.api.domain.DTO.DadosDetalhamentoTopico;
import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.topico.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> criarTopico(@RequestBody @Valid DadosCadastroTopicos dados, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoService.criarTopico(dados);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopicos>> listarTopicos(@PageableDefault(page = 0, size = 10, sort = {"curso"})Pageable paginacao) {
        Page<DadosListagemTopicos> topicos = topicoService.listarTopicos(paginacao);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhamentoDoTopico(@PathVariable Long id) {
        DadosDetalhamentoTopico detalhamento = topicoService.detalhamentoDoTopico(id);
        return ResponseEntity.ok(detalhamento);
    }
}