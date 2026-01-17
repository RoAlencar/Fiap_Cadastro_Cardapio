package br.com.fiap.app.cardapio.application.usecase;


import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.application.port.DeletaCardapioUseCasePort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletaCardapioUseCase implements DeletaCardapioUseCasePort {

    private final CardapioRepositoryPort cardapioRepositoryPort;

    @Override
    public void deletaCardapio(Long id) throws CardapioNotFoundException {
        Cardapio cardapio = cardapioRepositoryPort.findById(id).orElseThrow(CardapioNotFoundException::new);
        cardapioRepositoryPort.deletaById(cardapio.getId());
    }
}
