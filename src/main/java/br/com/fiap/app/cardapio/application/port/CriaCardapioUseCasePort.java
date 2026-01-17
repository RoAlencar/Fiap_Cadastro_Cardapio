package br.com.fiap.app.cardapio.application.port;

import br.com.fiap.app.cardapio.application.dto.request.CriarCardapioDto;
import br.com.fiap.app.cardapio.application.dto.response.CriarCardapioResponse;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.AvailabilityIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.DescriptionIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NameIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PhotoOfTheProductIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PriceIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.RequestRequiredException;

public interface CriaCardapioUseCasePort {

    CriarCardapioResponse criaCardapio(CriarCardapioDto dto) throws RequestRequiredException, DescriptionIsRequiredException, PriceIsRequiredException, NameIsRequiredException, PhotoOfTheProductIsRequiredException, AvailabilityIsRequiredException;
}
