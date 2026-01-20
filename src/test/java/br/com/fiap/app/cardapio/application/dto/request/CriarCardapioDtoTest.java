package br.com.fiap.app.cardapio.application.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CriarCardapioDtoTest {

    private CriarCardapioDto criarCardapioDto;

    @BeforeEach
    void setUp() {
        criarCardapioDto = CriarCardapioDto.builder()
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
        assertNotNull(criarCardapioDto);
        assertEquals(1L, criarCardapioDto.getId());
        assertEquals("Pizza Margherita", criarCardapioDto.getNome());
        assertEquals("Pizza clássica com tomate e mozzarela", criarCardapioDto.getDescricao());
        assertEquals(new BigDecimal("45.00"), criarCardapioDto.getPreco());
        assertEquals(true, criarCardapioDto.getDisponivel());
        assertEquals("https://example.com/pizza.jpg", criarCardapioDto.getFotoProduto());
    }

    @Test
    @DisplayName("Deve criar um DTO vazio")
    void testCriarDtoVazio() {
        CriarCardapioDto dtoVazio = new CriarCardapioDto();
        assertNotNull(dtoVazio);
        assertNull(dtoVazio.getId());
        assertNull(dtoVazio.getNome());
    }

    @Test
    @DisplayName("Deve copiar um DTO para novo DTO")
    void testCopiarDto() {
        CriarCardapioDto dtoCopia = new CriarCardapioDto(criarCardapioDto);

        assertNotNull(dtoCopia);
        assertEquals(criarCardapioDto.getId(), dtoCopia.getId());
        assertEquals(criarCardapioDto.getNome(), dtoCopia.getNome());
        assertEquals(criarCardapioDto.getDescricao(), dtoCopia.getDescricao());
        assertEquals(criarCardapioDto.getPreco(), dtoCopia.getPreco());
        assertEquals(criarCardapioDto.getDisponivel(), dtoCopia.getDisponivel());
        assertEquals(criarCardapioDto.getFotoProduto(), dtoCopia.getFotoProduto());
    }

    @Test
    @DisplayName("Deve settar campos individuais")
    void testSettarCamposIndividuais() {
        CriarCardapioDto dto = new CriarCardapioDto();
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
        CriarCardapioDto dto = CriarCardapioDto.builder()
                .nome("Salada")
                .descricao("Salada fresca")
                .preco(new BigDecimal("25.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/salada.jpg")
                .build();

        assertNotNull(dto);
        assertEquals("Salada", dto.getNome());
    }

    @Test
    @DisplayName("Deve permitir atualizar nome após criação")
    void testAtualizarNomeAposCriacao() {
        criarCardapioDto.setNome("Pizza Pepperoni");
        assertEquals("Pizza Pepperoni", criarCardapioDto.getNome());
    }

    @Test
    @DisplayName("Deve permitir atualizar preço para valores diferentes")
    void testAtualizarPreco() {
        criarCardapioDto.setPreco(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("50.00"), criarCardapioDto.getPreco());

        criarCardapioDto.setPreco(new BigDecimal("0.00"));
        assertEquals(new BigDecimal("0.00"), criarCardapioDto.getPreco());
    }

    @Test
    @DisplayName("Deve permitir disponível ser true ou false")
    void testDisponivel() {
        criarCardapioDto.setDisponivel(false);
        assertEquals(false, criarCardapioDto.getDisponivel());

        criarCardapioDto.setDisponivel(true);
        assertEquals(true, criarCardapioDto.getDisponivel());
    }
}
