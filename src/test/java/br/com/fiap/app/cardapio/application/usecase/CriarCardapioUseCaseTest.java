package br.com.fiap.app.cardapio.application.usecase;

import br.com.fiap.app.cardapio.application.dto.request.CriarCardapioDto;
import br.com.fiap.app.cardapio.application.dto.response.CriarCardapioResponse;
import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.AvailabilityIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.DescriptionIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NameIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PhotoOfTheProductIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PriceIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.RequestRequiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarCardapioUseCaseTest {

    @Mock
    private CardapioRepositoryPort cardapioRepositoryPort;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CriarCardapioUseCase criarCardapioUseCase;

    private CriarCardapioDto criarCardapioDto;
    private Cardapio cardapioSalvo;
    private CriarCardapioResponse response;

    @BeforeEach
    void setUp() {
        criarCardapioDto = CriarCardapioDto.builder()
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        cardapioSalvo = Cardapio.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        response = new CriarCardapioResponse();
        response.setId(1L);
        response.setNome("Pizza Margherita");
        response.setDescricao("Pizza clássica com tomate e mozzarela");
        response.setPreco(new BigDecimal("45.00"));
        response.setDisponivel(true);
        response.setFotoProduto("https://example.com/pizza.jpg");
    }

    @Test
    @DisplayName("Deve criar um cardápio com sucesso com todos os campos válidos")
    void testCriarCardapioComSucesso() throws Exception {
        when(cardapioRepositoryPort.save(any(Cardapio.class))).thenReturn(cardapioSalvo);
        when(modelMapper.map(cardapioSalvo, CriarCardapioResponse.class)).thenReturn(response);

        CriarCardapioResponse resultado = criarCardapioUseCase.criaCardapio(criarCardapioDto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pizza Margherita", resultado.getNome());
        assertEquals(new BigDecimal("45.00"), resultado.getPreco());
        verify(cardapioRepositoryPort, times(1)).save(any(Cardapio.class));
        verify(modelMapper, times(1)).map(cardapioSalvo, CriarCardapioResponse.class);
    }

    @Test
    @DisplayName("Deve lançar RequestRequiredException quando DTO é nulo")
    void testCriarCardapioComDtoNulo() {
        assertThrows(RequestRequiredException.class, () -> {
            criarCardapioUseCase.criaCardapio(null);
        });
    }

    @Test
    @DisplayName("Deve lançar NameIsRequiredException quando nome é nulo")
    void testCriarCardapioComNomeNulo() {
        criarCardapioDto.setNome(null);
        assertThrows(NameIsRequiredException.class, () -> {
            criarCardapioUseCase.criaCardapio(criarCardapioDto);
        });
    }

    @Test
    @DisplayName("Deve lançar PriceIsRequiredException quando preço é nulo")
    void testCriarCardapioComPrecoNulo() {
        criarCardapioDto.setPreco(null);
        assertThrows(PriceIsRequiredException.class, () -> {
            criarCardapioUseCase.criaCardapio(criarCardapioDto);
        });
    }

    @Test
    @DisplayName("Deve lançar DescriptionIsRequiredException quando descrição é nula")
    void testCriarCardapioComDescricaoNula() {
        criarCardapioDto.setDescricao(null);
        assertThrows(DescriptionIsRequiredException.class, () -> {
            criarCardapioUseCase.criaCardapio(criarCardapioDto);
        });
    }

    @Test
    @DisplayName("Deve lançar AvailabilityIsRequiredException quando disponível é nulo")
    void testCriarCardapioComDispoonivelNulo() {
        criarCardapioDto.setDisponivel(null);
        assertThrows(AvailabilityIsRequiredException.class, () -> {
            criarCardapioUseCase.criaCardapio(criarCardapioDto);
        });
    }

    @Test
    @DisplayName("Deve lançar PhotoOfTheProductIsRequiredException quando foto do produto é nula")
    void testCriarCardapioComFotoNula() {
        criarCardapioDto.setFotoProduto(null);
        assertThrows(PhotoOfTheProductIsRequiredException.class, () -> {
            criarCardapioUseCase.criaCardapio(criarCardapioDto);
        });
    }

    @Test
    @DisplayName("Deve validar ordem de verificação de campos obrigatórios")
    void testValidacaoOrdenada() {
        CriarCardapioDto dtoVazio = new CriarCardapioDto();
        assertThrows(NameIsRequiredException.class, () -> {
            criarCardapioUseCase.criaCardapio(dtoVazio);
        });
    }

    @Test
    @DisplayName("Deve criar cardápio com valores válidos mínimos")
    void testCriarCardapioComValoresMinimos() throws Exception {
        CriarCardapioDto dtoMinimo = CriarCardapioDto.builder()
                .nome("X")
                .descricao("D")
                .preco(BigDecimal.ZERO)
                .disponivel(false)
                .fotoProduto("foto")
                .build();

        Cardapio cardapioMinimo = Cardapio.builder()
                .id(2L)
                .nome("X")
                .descricao("D")
                .preco(BigDecimal.ZERO)
                .disponivel(false)
                .fotoProduto("foto")
                .build();

        CriarCardapioResponse responseMinima = new CriarCardapioResponse();
        responseMinima.setId(2L);
        responseMinima.setNome("X");

        when(cardapioRepositoryPort.save(any(Cardapio.class))).thenReturn(cardapioMinimo);
        when(modelMapper.map(cardapioMinimo, CriarCardapioResponse.class)).thenReturn(responseMinima);

        CriarCardapioResponse resultado = criarCardapioUseCase.criaCardapio(dtoMinimo);

        assertNotNull(resultado);
        verify(cardapioRepositoryPort, times(1)).save(any(Cardapio.class));
    }
}
