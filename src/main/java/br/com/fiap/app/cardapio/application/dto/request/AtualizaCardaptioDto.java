package br.com.fiap.app.cardapio.application.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizaCardaptioDto {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;
    private String fotoProduto;

    public AtualizaCardaptioDto(AtualizaCardaptioDto cardapio) {
        this.id = cardapio.getId();
        this.nome = cardapio.getNome();
        this.descricao = cardapio.getDescricao();
        this.preco = cardapio.getPreco();
        this.disponivel = cardapio.getDisponivel();
        this.fotoProduto = cardapio.getFotoProduto();
    }
}
