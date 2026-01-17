package br.com.fiap.app.cardapio.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BuscaCardapioResponse extends CardapioBaseResponse {

    public BuscaCardapioResponse(CardapioBaseResponse cardapioBaseResponse) {
        super(cardapioBaseResponse);
    }
}
