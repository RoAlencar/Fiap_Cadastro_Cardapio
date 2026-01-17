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
public class CriarCardapioDto {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;
    private String fotoProduto;

    public CriarCardapioDto(CriarCardapioDto criarCardapioDto) {
        this.id = criarCardapioDto.getId();
        this.nome = criarCardapioDto.getNome();
        this.descricao = criarCardapioDto.getDescricao();
        this.preco = criarCardapioDto.getPreco();
        this.disponivel = criarCardapioDto.getDisponivel();
        this.fotoProduto = criarCardapioDto.getFotoProduto();
    }
}
