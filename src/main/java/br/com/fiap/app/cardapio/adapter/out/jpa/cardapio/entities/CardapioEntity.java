package br.com.fiap.app.cardapio.adapter.out.jpa.cardapio.entities;

import br.com.fiap.app.cardapio.domain.Cardapio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "CORE_CARDAPIO")
public class CardapioEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRECO", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "DISPONIBILIDADE")
    private Boolean disponivel;

    @Column(name = "FOTO_PRODUTO")
    private String fotoProduto;


    public Cardapio toDomain() {
        return Cardapio.builder()
                .id(this.id)
                .nome(this.nome)
                .descricao(this.descricao)
                .preco(this.preco)
                .disponivel(this.disponivel)
                .fotoProduto(this.fotoProduto)
                .build();
    }
}
