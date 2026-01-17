package br.com.fiap.app.cardapio.application.port;

import br.com.fiap.app.cardapio.application.dto.request.AtualizaCardaptioDto;
import br.com.fiap.app.cardapio.application.dto.response.AtualizaCardapioResponse;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NoChangesDetectedException;

public interface AtualizaCardapioUseCasePort {

    AtualizaCardapioResponse atualizaCardapio(AtualizaCardaptioDto dto) throws CardapioNotFoundException, NoChangesDetectedException;
}
