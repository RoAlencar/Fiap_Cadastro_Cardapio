package br.com.fiap.app.cardapio.application.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AtualizaCardapioDtoTest {

    private AtualizaCardaptioDto atualizaCardapioDto;

    @BeforeEach
    void setUp() {
        atualizaCardapioDto = AtualizaCardaptioDto.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();
    }

    @Test
    @DisplayName("Deve criar um DTO com todos os campos")
    void testCriarDtoComTodosCampos() {
        assertNotNull(atualizaCardapioDto);
        assertEquals(1L, atualizaCardapioDto.getId());
        assertEquals("Pizza Margherita", atualizaCardapioDto.getNome());
        assertEquals("Pizza clássica com tomate e mozzarela", atualizaCardapioDto.getDescricao());
        assertEquals(new BigDecimal("45.00"), atualizaCardapioDto.getPreco());
        assertEquals(true, atualizaCardapioDto.getDisponivel());
        assertEquals("https://example.com/pizza.jpg", atualizaCardapioDto.getFotoProduto());
    }

    @Test
    @DisplayName("Deve criar um DTO vazio")
    void testCriarDtoVazio() {
        AtualizaCardaptioDto dtoVazio = new AtualizaCardaptioDto();
        assertNotNull(dtoVazio);
        assertNull(dtoVazio.getId());
        assertNull(dtoVazio.getNome());
    }

    @Test
    @DisplayName("Deve copiar um DTO para novo DTO")
    void testCopiarDto() {
        AtualizaCardaptioDto dtoCopia = new AtualizaCardaptioDto(atualizaCardapioDto);

        assertNotNull(dtoCopia);
        assertEquals(atualizaCardapioDto.getId(), dtoCopia.getId());
        assertEquals(atualizaCardapioDto.getNome(), dtoCopia.getNome());
        assertEquals(atualizaCardapioDto.getDescricao(), dtoCopia.getDescricao());
        assertEquals(atualizaCardapioDto.getPreco(), dtoCopia.getPreco());
        assertEquals(atualizaCardapioDto.getDisponivel(), dtoCopia.getDisponivel());
        assertEquals(atualizaCardapioDto.getFotoProduto(), dtoCopia.getFotoProduto());
    }

    @Test
    @DisplayName("Deve settar campos individuais")
    void testSettarCamposIndividuais() {
        AtualizaCardaptioDto dto = new AtualizaCardaptioDto();
        dto.setId(2L);
        dto.setNome("Hambúrguer");
        dto.setDescricao("Hambúrguer artesanal");
        dto.setPreco(new BigDecimal("30.00"));
        dto.setDisponivel(false);
        dto.setFotoProduto("https://example.com/hamburger.jpg");

        assertEquals(2L, dto.getId());
        assertEquals("Hambúrguer", dto.getNome());
        assertEquals("Hambúrguer artesanal", dto.getDescricao());
        assertEquals(new BigDecimal("30.00"), dto.getPreco());
        assertEquals(false, dto.getDisponivel());
        assertEquals("https://example.com/hamburger.jpg", dto.getFotoProduto());
    }

    @Test
    @DisplayName("Deve suportar builder pattern")
    void testBuilderPattern() {
        AtualizaCardaptioDto dto = AtualizaCardaptioDto.builder()
                .id(3L)
                .nome("Salada")
                .descricao("Salada fresca")
                .preco(new BigDecimal("25.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/salada.jpg")
                .build();

        assertNotNull(dto);
        assertEquals(3L, dto.getId());
        assertEquals("Salada", dto.getNome());
    }

    @Test
    @DisplayName("Deve permitir atualizar nome após criação")
    void testAtualizarNomeAposCriacao() {
        atualizaCardapioDto.setNome("Pizza Pepperoni");
        assertEquals("Pizza Pepperoni", atualizaCardapioDto.getNome());
    }

    @Test
    @DisplayName("Deve permitir atualizar preço para valores diferentes")
    void testAtualizarPreco() {
        atualizaCardapioDto.setPreco(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("50.00"), atualizaCardapioDto.getPreco());

        atualizaCardapioDto.setPreco(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), atualizaCardapioDto.getPreco());
    }

    @Test
    @DisplayName("Deve permitir disponível ser true ou false")
    void testDisponivel() {
        atualizaCardapioDto.setDisponivel(false);
        assertEquals(false, atualizaCardapioDto.getDisponivel());

        atualizaCardapioDto.setDisponivel(true);
        assertEquals(true, atualizaCardapioDto.getDisponivel());
    }

    @Test
    @DisplayName("Deve permitir atualizar ID")
    void testAtualizarId() {
        atualizaCardapioDto.setId(10L);
        assertEquals(10L, atualizaCardapioDto.getId());
    }

    @Test
    @DisplayName("Deve permitir atualizar descrição")
    void testAtualizarDescricao() {
        String novaDescricao = "Pizza com molho especial e queijo premium";
        atualizaCardapioDto.setDescricao(novaDescricao);
        assertEquals(novaDescricao, atualizaCardapioDto.getDescricao());
    }

    @Test
    @DisplayName("Deve permitir atualizar fotoProduto")
    void testAtualizarFotoProduto() {
        String novaFoto = "https://example.com/nova-pizza.jpg";
        atualizaCardapioDto.setFotoProduto(novaFoto);
        assertEquals(novaFoto, atualizaCardapioDto.getFotoProduto());
    }
}
