package br.com.fiap.app.cardapio.application.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CardapioBaseResponseTest {

    @Test
    void testBuilderAndGetters() {
        CardapioBaseResponse cardapio = CardapioBaseResponse.builder()
                .id(1L)
                .nome("Pizza")
                .descricao("Pizza de calabresa")
                .preco(new BigDecimal("39.90"))
                .disponivel(true)
                .fotoProduto("pizza.jpg")
                .build();

        assertEquals(1L, cardapio.getId());
        assertEquals("Pizza", cardapio.getNome());
        assertEquals("Pizza de calabresa", cardapio.getDescricao());
        assertEquals(new BigDecimal("39.90"), cardapio.getPreco());
        assertTrue(cardapio.getDisponivel());
        assertEquals("pizza.jpg", cardapio.getFotoProduto());
    }

    @Test
    void testCopyConstructor() {
        CardapioBaseResponse original = CardapioBaseResponse.builder()
                .id(2L)
                .nome("Hamburguer")
                .descricao("Hamburguer artesanal")
                .preco(new BigDecimal("29.90"))
                .disponivel(false)
                .fotoProduto("hamburguer.jpg")
                .build();

        CardapioBaseResponse copy = new CardapioBaseResponse(original);

        assertEquals(original.getId(), copy.getId());
        assertEquals(original.getNome(), copy.getNome());
        assertEquals(original.getDescricao(), copy.getDescricao());
        assertEquals(original.getPreco(), copy.getPreco());
        assertEquals(original.getDisponivel(), copy.getDisponivel());
        assertEquals(original.getFotoProduto(), copy.getFotoProduto());
    }

    @Test
    void testSetters() {
        CardapioBaseResponse cardapio = new CardapioBaseResponse();
        cardapio.setId(3L);
        cardapio.setNome("Sushi");
        cardapio.setDescricao("Sushi de salmão");
        cardapio.setPreco(new BigDecimal("49.90"));
        cardapio.setDisponivel(true);
        cardapio.setFotoProduto("sushi.jpg");

        assertEquals(3L, cardapio.getId());
        assertEquals("Sushi", cardapio.getNome());
        assertEquals("Sushi de salmão", cardapio.getDescricao());
        assertEquals(new BigDecimal("49.90"), cardapio.getPreco());
        assertTrue(cardapio.getDisponivel());
        assertEquals("sushi.jpg", cardapio.getFotoProduto());
    }
}
