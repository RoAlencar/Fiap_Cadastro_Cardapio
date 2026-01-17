package br.com.fiap.app.cardapio.application.port;

import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;

public interface DeletaCardapioUseCasePort {

    void deletaCardapio(Long id) throws CardapioNotFoundException;
}
