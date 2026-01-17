package br.com.fiap.app.cardapio.adapter.in.web;

import br.com.fiap.app.cardapio.application.dto.request.AtualizaCardaptioDto;
import br.com.fiap.app.cardapio.application.dto.request.CriarCardapioDto;
import br.com.fiap.app.cardapio.application.dto.response.AtualizaCardapioResponse;
import br.com.fiap.app.cardapio.application.dto.response.BuscaCardapioResponse;
import br.com.fiap.app.cardapio.application.dto.response.CriarCardapioResponse;
import br.com.fiap.app.cardapio.application.port.AtualizaCardapioUseCasePort;
import br.com.fiap.app.cardapio.application.port.BuscaCardapioUseCasePort;
import br.com.fiap.app.cardapio.application.port.CriaCardapioUseCasePort;
import br.com.fiap.app.cardapio.application.port.DeletaCardapioUseCasePort;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.AvailabilityIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.DescriptionIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NameIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PhotoOfTheProductIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PriceIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.RequestRequiredException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("app/v1/cardapio")
@RequiredArgsConstructor
public class CadastraCardapioController {

    private final AtualizaCardapioUseCasePort atualizaCardapioUseCase;
    private final BuscaCardapioUseCasePort buscaCardapioUseCase;
    private final CriaCardapioUseCasePort criaCardapioUseCase;
    private final DeletaCardapioUseCasePort deletaCardapioUseCase;

    @GetMapping
    public ResponseEntity<List<BuscaCardapioResponse>> buscaTodosOsItensDoCardapios() {
        log.info("[Cardapio - Busca Todos Os Itens Do Cardapios] Iniciando Processo");
        return new ResponseEntity<>(buscaCardapioUseCase.buscaTodosCardapios(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<BuscaCardapioResponse>> buscaItemDoCardapioPorNome(@PathVariable String name) throws CardapioNotFoundException {
        log.info("[Cardapio - Busca Item Do Cardapio] Iniciando Processo");
        List<BuscaCardapioResponse> response = buscaCardapioUseCase.buscaItemDoCardapioPorNome(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CriarCardapioResponse> criarCardapio(@RequestBody CriarCardapioDto dto) throws RequestRequiredException,
            DescriptionIsRequiredException, PriceIsRequiredException, NameIsRequiredException, PhotoOfTheProductIsRequiredException,
            AvailabilityIsRequiredException {
        log.info("[Cardapio - Criar Cardapio] Iniciando Processo");
        return new ResponseEntity<>(criaCardapioUseCase.criaCardapio(dto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AtualizaCardapioResponse> atualizaCardapio(@Valid @RequestBody AtualizaCardaptioDto dto) throws NoChangesDetectedException,
            CardapioNotFoundException {
        log.info("[Cardapio - Atualizar cardapio] Iniciando Processo");
        AtualizaCardapioResponse response = atualizaCardapioUseCase.atualizaCardapio(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCardapio(@PathVariable Long id) throws CardapioNotFoundException {
        log.info("[Cardapio - Deleta Cardapio] Iniciando Processo");
        deletaCardapioUseCase.deletaCardapio(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
