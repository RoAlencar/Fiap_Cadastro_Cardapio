package br.com.fiap.app.cardapio.domain;

import br.com.fiap.app.cardapio.adapter.out.jpa.cardapio.entities.CardapioEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cardapio {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;
    private String fotoProduto;

    public Cardapio(Cardapio cardapio) {}

    public CardapioEntity toEntity() {
        return CardapioEntity.builder()
                .id(this.id)
                .nome(this.nome)
                .descricao(this.descricao)
                .preco(this.preco)
                .disponivel(this.disponivel)
                .fotoProduto(this.fotoProduto)
                .build();
    }
}
