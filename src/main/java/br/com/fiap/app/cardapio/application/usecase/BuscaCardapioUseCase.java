package br.com.fiap.app.cardapio.application.usecase;

import br.com.fiap.app.cardapio.application.dto.response.BuscaCardapioResponse;
import br.com.fiap.app.cardapio.application.port.BuscaCardapioUseCasePort;
import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscaCardapioUseCase implements BuscaCardapioUseCasePort {

    private final CardapioRepositoryPort cardapioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public List<BuscaCardapioResponse> buscaTodosCardapios() {
        return cardapioRepositoryPort.findAll().stream()
                .map(entity -> modelMapper.map(entity, BuscaCardapioResponse.class)).toList();
    }

    @Override
    public List<BuscaCardapioResponse> buscaItemDoCardapioPorNome(String nome)
            throws CardapioNotFoundException {

        List<Cardapio> cardapios = cardapioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent(nome);

        if(cardapios.isEmpty()){
            throw new CardapioNotFoundException();
        }

        return cardapios.stream()
                .map(entity -> modelMapper.map(entity, BuscaCardapioResponse.class))
                .toList();
    }
}
