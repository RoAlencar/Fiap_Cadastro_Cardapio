package br.com.fiap.app.cardapio.infrastructure.exception;

import br.com.fiap.app.cardapio.infrastructure.exception.custom.AvailabilityIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.DescriptionIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NameIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PhotoOfTheProductIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PriceIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.RequestRequiredException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AvailabilityIsRequiredException.class)
    public ResponseEntity<ProblemDetail> handleAvailabilityIsRequiredException(AvailabilityIsRequiredException ex,
                                                                               HttpServletRequest request) {
        log.warn("[Cardapio - Cadastra Cardapio] - A informação de disponibilidade do produto é obrigatória");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,"A informação de disponibilidade do produto é obrigatória",
                        request));
    }

    @ExceptionHandler(CardapioNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCardapioNotFoundException(CardapioNotFoundException ex,HttpServletRequest request) {
        String httpMethod = request.getMethod();
        log.warn("[Cardapio - {}] - O Cardapio não foi encontrado", httpMethod);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.NOT_FOUND,"O Cardapio não foi encontrado", request));
    }

    @ExceptionHandler(DescriptionIsRequiredException.class)
    public ResponseEntity<ProblemDetail> handleDescriptionIsRequiredException(DescriptionIsRequiredException ex,HttpServletRequest request) {
        log.warn("[Cardapio - Cadastra Cardapio] - A descrição do produto é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,"A descrição do produto é obrigatória",
                        request));
    }

    @ExceptionHandler(NameIsRequiredException.class)
    public ResponseEntity<ProblemDetail> handleNameIsRequiredException(NameIsRequiredException ex,HttpServletRequest request) {
        log.warn("[Cardapio - Cadastra Cardapio] - O nome do produto é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,"O nome do produto é obrigatório",
                        request));
    }

    @ExceptionHandler(PhotoOfTheProductIsRequiredException.class)
    public ResponseEntity<ProblemDetail> handlePhotoOfTheProductIsRequiredException(PhotoOfTheProductIsRequiredException ex, HttpServletRequest request) {
        log.warn("[Cardapio - Cadastra Cardapio] - A foto do produto é obrigatória");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,"A foto do produto é obrigatória",
                        request));
    }

    @ExceptionHandler(PriceIsRequiredException.class)
    public ResponseEntity<ProblemDetail> handlePriceIsRequiredException(PriceIsRequiredException ex, HttpServletRequest request) {
        log.warn("[Cardapio - Cadastra Cardapio] - O preço do produto é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,"O preço do produto é obrigatório",
                        request));
    }

    @ExceptionHandler(RequestRequiredException.class)
    public ResponseEntity<ProblemDetail> handleRequestRequiredException(RequestRequiredException ex, HttpServletRequest request) {
        log.warn("[Cardapio - Cadastra Cardapio] - A requisição é obrigatória");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "A Requisição é obrigatória",
                        request));
    }

    @ExceptionHandler(NoChangesDetectedException.class)
    public ResponseEntity<ProblemDetail> handleNoChangesDetectedException(NoChangesDetectedException ex,
                                                                          HttpServletRequest request) {
        log.error("[Cardapio - Atualiza Cardapio ] Não foram detectadas alterações nos dados do Cardapio");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                        "Não foram detectadas alterações nos dados do Cardapio",
                        request));
    }

    private ProblemDetail buildProblemDetail(HttpStatus status, String detail, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(status.getReasonPhrase());
        problem.setDetail(detail);
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setType(URI.create("https://api.fiap.com/errors/" + status.value()));
        return problem;
    }
}
