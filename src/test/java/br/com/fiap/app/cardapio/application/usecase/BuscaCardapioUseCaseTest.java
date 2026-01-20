package br.com.fiap.app.cardapio.application.usecase;

import br.com.fiap.app.cardapio.application.dto.response.BuscaCardapioResponse;
import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscaCardapioUseCaseTest {

    @Mock
    private CardapioRepositoryPort cardapioRepositoryPort;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BuscaCardapioUseCase buscaCardapioUseCase;

    private Cardapio cardapio1;
    private Cardapio cardapio2;
    private BuscaCardapioResponse response1;
    private BuscaCardapioResponse response2;

    @BeforeEach
    void setUp() {
        cardapio1 = Cardapio.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        cardapio2 = Cardapio.builder()
                .id(2L)
                .nome("Hambúrguer Artesanal")
                .descricao("Hambúrguer com carne fresca")
                .preco(new BigDecimal("30.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/hamburger.jpg")
                .build();

        response1 = new BuscaCardapioResponse();
        response1.setId(1L);
        response1.setNome("Pizza Margherita");
        response1.setDescricao("Pizza clássica com tomate e mozzarela");
        response1.setPreco(new BigDecimal("45.00"));
        response1.setDisponivel(true);
        response1.setFotoProduto("https://example.com/pizza.jpg");

        response2 = new BuscaCardapioResponse();
        response2.setId(2L);
        response2.setNome("Hambúrguer Artesanal");
        response2.setDescricao("Hambúrguer com carne fresca");
        response2.setPreco(new BigDecimal("30.00"));
        response2.setDisponivel(true);
        response2.setFotoProduto("https://example.com/hamburger.jpg");
    }

    @Test
    @DisplayName("Deve buscar todos os cardápios com sucesso")
    void testBuscaTodosCardapios() {
        when(cardapioRepositoryPort.findAll()).thenReturn(List.of(cardapio1, cardapio2));
        when(modelMapper.map(cardapio1, BuscaCardapioResponse.class)).thenReturn(response1);
        when(modelMapper.map(cardapio2, BuscaCardapioResponse.class)).thenReturn(response2);

        List<BuscaCardapioResponse> resultado = buscaCardapioUseCase.buscaTodosCardapios();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Pizza Margherita", resultado.get(0).getNome());
        assertEquals("Hambúrguer Artesanal", resultado.get(1).getNome());
        verify(cardapioRepositoryPort, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há cardápios cadastrados")
    void testBuscaTodosCardapiosVazio() {
        when(cardapioRepositoryPort.findAll()).thenReturn(List.of());

        List<BuscaCardapioResponse> resultado = buscaCardapioUseCase.buscaTodosCardapios();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        assertEquals(0, resultado.size());
        verify(cardapioRepositoryPort, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar cardápio por nome com sucesso")
    void testBuscaCardapioPorNomeComSucesso() throws CardapioNotFoundException {
        when(cardapioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("Pizza"))
                .thenReturn(List.of(cardapio1));
        when(modelMapper.map(cardapio1, BuscaCardapioResponse.class)).thenReturn(response1);

        List<BuscaCardapioResponse> resultado = 
                buscaCardapioUseCase.buscaItemDoCardapioPorNome("Pizza");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Pizza Margherita", resultado.get(0).getNome());
        verify(cardapioRepositoryPort, times(1))
                .findByNomeLikeIgnoreCaseAndAccent("Pizza");
    }

    @Test
    @DisplayName("Deve buscar múltiplos cardápios com mesmo padrão de nome")
    void testBuscaCardapioPorNomeMultiplos() throws CardapioNotFoundException {
        Cardapio cardapio3 = Cardapio.builder()
                .id(3L)
                .nome("Pizza Pepperoni")
                .descricao("Pizza com pepperoni")
                .preco(new BigDecimal("50.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza-pepperoni.jpg")
                .build();

        BuscaCardapioResponse response3 = new BuscaCardapioResponse();
        response3.setId(3L);
        response3.setNome("Pizza Pepperoni");
        response3.setPreco(new BigDecimal("50.00"));

        when(cardapioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("Pizza"))
                .thenReturn(List.of(cardapio1, cardapio3));
        when(modelMapper.map(cardapio1, BuscaCardapioResponse.class)).thenReturn(response1);
        when(modelMapper.map(cardapio3, BuscaCardapioResponse.class)).thenReturn(response3);

        List<BuscaCardapioResponse> resultado = 
                buscaCardapioUseCase.buscaItemDoCardapioPorNome("Pizza");

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(cardapioRepositoryPort, times(1))
                .findByNomeLikeIgnoreCaseAndAccent("Pizza");
    }

    @Test
    @DisplayName("Deve lançar CardapioNotFoundException quando nenhum cardápio é encontrado")
    void testBuscaCardapioPorNomeNaoEncontrado() {
        when(cardapioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent(anyString()))
                .thenReturn(List.of());

        assertThrows(CardapioNotFoundException.class, () -> {
            buscaCardapioUseCase.buscaItemDoCardapioPorNome("PizzaNãoExiste");
        });

        verify(cardapioRepositoryPort, times(1))
                .findByNomeLikeIgnoreCaseAndAccent("PizzaNãoExiste");
    }

    @Test
    @DisplayName("Deve buscar cardápio case insensitive")
    void testBuscaCardapioCaseInsensitive() throws CardapioNotFoundException {
        when(cardapioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("pizza"))
                .thenReturn(List.of(cardapio1));
        when(modelMapper.map(cardapio1, BuscaCardapioResponse.class)).thenReturn(response1);

        List<BuscaCardapioResponse> resultado = 
                buscaCardapioUseCase.buscaItemDoCardapioPorNome("pizza");

        assertNotNull(resultado);
        assertEquals("Pizza Margherita", resultado.get(0).getNome());
    }

    @Test
    @DisplayName("Deve buscar cardápio com acentos removidos")
    void testBuscaCardapioComAcentos() throws CardapioNotFoundException {
        when(cardapioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("Hambúrguer"))
                .thenReturn(List.of(cardapio2));
        when(modelMapper.map(cardapio2, BuscaCardapioResponse.class)).thenReturn(response2);

        List<BuscaCardapioResponse> resultado = 
                buscaCardapioUseCase.buscaItemDoCardapioPorNome("Hambúrguer");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}
