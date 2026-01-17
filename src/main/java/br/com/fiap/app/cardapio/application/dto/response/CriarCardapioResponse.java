package br.com.fiap.app.cardapio.application.dto.response;

import br.com.fiap.app.cardapio.domain.Cardapio;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CriarCardapioResponse extends CardapioBaseResponse {

    public CriarCardapioResponse(CardapioBaseResponse response) {
        super(response);
    }
}
