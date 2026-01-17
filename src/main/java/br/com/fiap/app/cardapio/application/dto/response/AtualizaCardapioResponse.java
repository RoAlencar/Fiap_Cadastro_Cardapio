package br.com.fiap.app.cardapio.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AtualizaCardapioResponse extends CardapioBaseResponse {

    public AtualizaCardapioResponse(CardapioBaseResponse cardapioBaseResponse) {
        super(cardapioBaseResponse);
    }
}
