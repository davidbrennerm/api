package forum.hub.api.infra.erro;

import forum.hub.api.infra.dto.DadosErroValidacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException exception) {
        var erros = exception.getBindingResult().getFieldErrors();
        var body = erros.stream().map(DadosErroValidacao::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(body);
    }
}
