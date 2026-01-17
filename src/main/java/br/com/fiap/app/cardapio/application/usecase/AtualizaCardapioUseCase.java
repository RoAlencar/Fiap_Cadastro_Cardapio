package br.com.fiap.app.cardapio.application.usecase;

import br.com.fiap.app.cardapio.application.dto.request.AtualizaCardaptioDto;
import br.com.fiap.app.cardapio.application.dto.response.AtualizaCardapioResponse;
import br.com.fiap.app.cardapio.application.port.AtualizaCardapioUseCasePort;
import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NoChangesDetectedException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AtualizaCardapioUseCase implements AtualizaCardapioUseCasePort {

    private final CardapioRepositoryPort cardapioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public AtualizaCardapioResponse atualizaCardapio(AtualizaCardaptioDto dto) throws CardapioNotFoundException, NoChangesDetectedException {

        Cardapio cardapioExistente = cardapioRepositoryPort.findById(dto.getId())
                .orElseThrow(CardapioNotFoundException::new);

        if (isUnchanged(dto, cardapioExistente)){
            throw new NoChangesDetectedException();
        }

        try{
            cardapioExistente.setNome(dto.getNome());
            cardapioExistente.setDescricao(dto.getDescricao());
            cardapioExistente.setDisponivel(dto.getDisponivel());
            cardapioExistente.setPreco(dto.getPreco());
            cardapioExistente.setFotoProduto(dto.getFotoProduto());

            Cardapio cardapioAtualizado = cardapioRepositoryPort.save(cardapioExistente);
            return modelMapper.map( cardapioAtualizado, AtualizaCardapioResponse.class);
        } catch (Exception e){
            throw new CardapioNotFoundException();
        }

    }


    private boolean isUnchanged (AtualizaCardaptioDto dto, Cardapio cardapio) {
        return Objects.equals(dto.getNome(), cardapio.getNome()) &&
                Objects.equals(dto.getDescricao(), cardapio.getDescricao()) &&
                Objects.equals(dto.getPreco(), cardapio.getPreco()) &&
                Objects.equals(dto.getDisponivel(), cardapio.getDisponivel()) &&
                Objects.equals(dto.getFotoProduto(), cardapio.getFotoProduto());
    }
}
