package br.com.fiap.app.cardapio.application.usecase;

import br.com.fiap.app.cardapio.adapter.out.jpa.cardapio.repositories.CardapioJpaRepository;
import br.com.fiap.app.cardapio.application.dto.request.CriarCardapioDto;
import br.com.fiap.app.cardapio.application.dto.response.CriarCardapioResponse;
import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.application.port.CriaCardapioUseCasePort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.AvailabilityIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.DescriptionIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NameIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PhotoOfTheProductIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PriceIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.RequestRequiredException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarCardapioUseCase implements CriaCardapioUseCasePort {

    private final CardapioRepositoryPort cardapioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public CriarCardapioResponse criaCardapio(CriarCardapioDto dto) throws RequestRequiredException, DescriptionIsRequiredException,
            PriceIsRequiredException, NameIsRequiredException, PhotoOfTheProductIsRequiredException, AvailabilityIsRequiredException {

        validarCardapio(dto);

        Cardapio cardapio = Cardapio.builder()
                .nome(dto.getNome())
                .preco(dto.getPreco())
                .descricao(dto.getDescricao())
                .disponivel(dto.getDisponivel())
                .fotoProduto(dto.getFotoProduto())
                .build();

        return modelMapper.map(cardapioRepositoryPort.save(cardapio), CriarCardapioResponse.class);
    }

    private void validarCardapio(CriarCardapioDto dto) throws RequestRequiredException, NameIsRequiredException, PriceIsRequiredException,
            DescriptionIsRequiredException, AvailabilityIsRequiredException, PhotoOfTheProductIsRequiredException {

        if(dto == null) {
            throw new RequestRequiredException();
        }

        if(dto.getNome() == null) {
            throw new NameIsRequiredException();
        }

        if(dto.getPreco() == null) {
            throw new PriceIsRequiredException();
        }

        if(dto.getDescricao() == null) {
            throw new DescriptionIsRequiredException();
        }

        if(dto.getDisponivel() == null) {
            throw new AvailabilityIsRequiredException();
        }

        if (dto.getFotoProduto() == null) {
            throw new PhotoOfTheProductIsRequiredException();
        }
    }
}
