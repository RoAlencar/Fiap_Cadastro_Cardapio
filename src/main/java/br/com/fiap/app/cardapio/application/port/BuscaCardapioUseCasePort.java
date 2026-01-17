package br.com.fiap.app.cardapio.application.port;

import br.com.fiap.app.cardapio.application.dto.response.BuscaCardapioResponse;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;

import java.util.List;

public interface BuscaCardapioUseCasePort {

    List<BuscaCardapioResponse> buscaTodosCardapios();

    List<BuscaCardapioResponse> buscaItemDoCardapioPorNome(String name) throws CardapioNotFoundException;
}
