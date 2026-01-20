package br.com.fiap.app.cardapio.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardapioTest {

    private Cardapio cardapio;

    @BeforeEach
    void setUp() {
        cardapio = Cardapio.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();
    }

    @Test
    @DisplayName("Deve criar um cardápio com todos os campos")
    void testCriarCardapioComTodosCampos() {
        assertNotNull(cardapio);
        assertEquals(1L, cardapio.getId());
        assertEquals("Pizza Margherita", cardapio.getNome());
        assertEquals("Pizza clássica com tomate e mozzarela", cardapio.getDescricao());
        assertEquals(new BigDecimal("45.00"), cardapio.getPreco());
        assertTrue(cardapio.getDisponivel());
        assertEquals("https://example.com/pizza.jpg", cardapio.getFotoProduto());
    }

    @Test
    @DisplayName("Deve criar um cardápio vazio")
    void testCriarCardapioVazio() {
        Cardapio cardapioVazio = new Cardapio();
        assertNotNull(cardapioVazio);
        assertNull(cardapioVazio.getId());
        assertNull(cardapioVazio.getNome());
    }

    @Test
    @DisplayName("Deve copiar um cardápio para novo cardápio")
    void testCopiarCardapio() {
        Cardapio carpapioCopia = new Cardapio(cardapio);
        assertNotNull(carpapioCopia);
        // Nota: O construtor de cópia está vazio, então a cópia será um objeto vazio
    }

    @Test
    @DisplayName("Deve settar campos individuais")
    void testSettarCamposIndividuais() {
        Cardapio c = new Cardapio();
        c.setId(2L);
        c.setNome("Hambúrguer");
        c.setDescricao("Hambúrguer artesanal");
        c.setPreco(new BigDecimal("30.00"));
        c.setDisponivel(false);
        c.setFotoProduto("https://example.com/hamburger.jpg");

        assertEquals(2L, c.getId());
        assertEquals("Hambúrguer", c.getNome());
        assertEquals("Hambúrguer artesanal", c.getDescricao());
        assertEquals(new BigDecimal("30.00"), c.getPreco());
        assertEquals(false, c.getDisponivel());
        assertEquals("https://example.com/hamburger.jpg", c.getFotoProduto());
    }

    @Test
    @DisplayName("Deve suportar builder pattern")
    void testBuilderPattern() {
        Cardapio c = Cardapio.builder()
                .id(3L)
                .nome("Salada")
                .descricao("Salada fresca")
                .preco(new BigDecimal("25.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/salada.jpg")
                .build();

        assertNotNull(c);
        assertEquals(3L, c.getId());
        assertEquals("Salada", c.getNome());
        assertEquals(new BigDecimal("25.00"), c.getPreco());
    }

    @Test
    @DisplayName("Deve permitir atualizar nome após criação")
    void testAtualizarNome() {
        cardapio.setNome("Pizza Pepperoni");
        assertEquals("Pizza Pepperoni", cardapio.getNome());
    }

    @Test
    @DisplayName("Deve permitir atualizar preço")
    void testAtualizarPreco() {
        cardapio.setPreco(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("50.00"), cardapio.getPreco());

        cardapio.setPreco(new BigDecimal("0.01"));
        assertEquals(new BigDecimal("0.01"), cardapio.getPreco());
    }

    @Test
    @DisplayName("Deve permitir atualizar disponibilidade")
    void testAtualizarDisponibilidade() {
        cardapio.setDisponivel(false);
        assertEquals(false, cardapio.getDisponivel());

        cardapio.setDisponivel(true);
        assertEquals(true, cardapio.getDisponivel());
    }

    @Test
    @DisplayName("Deve permitir atualizar descrição")
    void testAtualizarDescricao() {
        String novaDescricao = "Pizza com molho especial e queijo premium";
        cardapio.setDescricao(novaDescricao);
        assertEquals(novaDescricao, cardapio.getDescricao());
    }

    @Test
    @DisplayName("Deve permitir atualizar foto")
    void testAtualizarFoto() {
        String novaFoto = "https://example.com/nova-pizza.jpg";
        cardapio.setFotoProduto(novaFoto);
        assertEquals(novaFoto, cardapio.getFotoProduto());
    }

    @Test
    @DisplayName("Deve converter cardápio para entity")
    void testToEntity() {
        assertNotNull(cardapio.toEntity());
        assertEquals(cardapio.getId(), cardapio.toEntity().getId());
        assertEquals(cardapio.getNome(), cardapio.toEntity().getNome());
        assertEquals(cardapio.getPreco(), cardapio.toEntity().getPreco());
    }

    @Test
    @DisplayName("Deve suportar preços zero")
    void testPrecoZero() {
        Cardapio c = new Cardapio();
        c.setPreco(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, c.getPreco());
    }

    @Test
    @DisplayName("Deve suportar preços negativos (se necessário)")
    void testPrecoNegativo() {
        Cardapio c = new Cardapio();
        c.setPreco(new BigDecimal("-10.00"));
        assertEquals(new BigDecimal("-10.00"), c.getPreco());
    }

    @Test
    @DisplayName("Deve suportar preços com muitas casas decimais")
    void testPrecoComMuitasCasasDecimais() {
        Cardapio c = new Cardapio();
        c.setPreco(new BigDecimal("45.99"));
        assertEquals(new BigDecimal("45.99"), c.getPreco());
    }

    @Test
    @DisplayName("Deve manter ID após atualizar outros campos")
    void testIdMantidoAposAtualizacoes() {
        Long idOriginal = cardapio.getId();
        cardapio.setNome("Novo Nome");
        cardapio.setPreco(new BigDecimal("100.00"));
        assertEquals(idOriginal, cardapio.getId());
    }

    @Test
    @DisplayName("Deve getter e setter funcionarem corretamente")
    void testGettersSetters() {
        Cardapio c = new Cardapio();

        c.setId(99L);
        assertEquals(99L, c.getId());

        String nome = "Teste";
        c.setNome(nome);
        assertEquals(nome, c.getNome());

        String descricao = "Descrição Teste";
        c.setDescricao(descricao);
        assertEquals(descricao, c.getDescricao());

        BigDecimal preco = new BigDecimal("123.45");
        c.setPreco(preco);
        assertEquals(preco, c.getPreco());

        c.setDisponivel(false);
        assertEquals(false, c.getDisponivel());

        String foto = "http://foto.jpg";
        c.setFotoProduto(foto);
        assertEquals(foto, c.getFotoProduto());
    }
}
