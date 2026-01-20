package br.com.fiap.app.cardapio.application.usecase;

import br.com.fiap.app.cardapio.application.dto.request.AtualizaCardaptioDto;
import br.com.fiap.app.cardapio.application.dto.response.AtualizaCardapioResponse;
import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NoChangesDetectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizaCardapioUseCaseTest {

    @Mock
    private CardapioRepositoryPort cardapioRepositoryPort;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AtualizaCardapioUseCase atualizaCardapioUseCase;

    private Cardapio cardapioExistente;
    private Cardapio cardapioAtualizado;
    private AtualizaCardaptioDto atualizaDto;
    private AtualizaCardapioResponse response;

    @BeforeEach
    void setUp() {
        cardapioExistente = Cardapio.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        cardapioAtualizado = Cardapio.builder()
                .id(1L)
                .nome("Pizza Margherita Premium")
                .descricao("Pizza clássica com tomate e mozzarela fresca")
                .preco(new BigDecimal("55.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza-premium.jpg")
                .build();

        atualizaDto = AtualizaCardaptioDto.builder()
                .id(1L)
                .nome("Pizza Margherita Premium")
                .descricao("Pizza clássica com tomate e mozzarela fresca")
                .preco(new BigDecimal("55.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza-premium.jpg")
                .build();

        response = new AtualizaCardapioResponse();
        response.setId(1L);
        response.setNome("Pizza Margherita Premium");
        response.setDescricao("Pizza clássica com tomate e mozzarela fresca");
        response.setPreco(new BigDecimal("55.00"));
        response.setDisponivel(true);
        response.setFotoProduto("https://example.com/pizza-premium.jpg");
    }

    @Test
    @DisplayName("Deve atualizar um cardápio com sucesso")
    void testAtualizaCardapioComSucesso() throws Exception {
        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioRepositoryPort.save(any(Cardapio.class))).thenReturn(cardapioAtualizado);
        when(modelMapper.map(cardapioAtualizado, AtualizaCardapioResponse.class)).thenReturn(response);

        AtualizaCardapioResponse resultado = atualizaCardapioUseCase.atualizaCardapio(atualizaDto);

        assertNotNull(resultado);
        assertEquals("Pizza Margherita Premium", resultado.getNome());
        assertEquals(new BigDecimal("55.00"), resultado.getPreco());
        verify(cardapioRepositoryPort, times(1)).findById(1L);
        verify(cardapioRepositoryPort, times(1)).save(any(Cardapio.class));
    }

    @Test
    @DisplayName("Deve lançar CardapioNotFoundException quando cardápio não existe")
    void testAtualizaCardapioNaoEncontrado() {
        when(cardapioRepositoryPort.findById(999L)).thenReturn(Optional.empty());

        AtualizaCardaptioDto dtoInexistente = AtualizaCardaptioDto.builder()
                .id(999L)
                .nome("Pizza")
                .build();

        assertThrows(CardapioNotFoundException.class, () -> {
            atualizaCardapioUseCase.atualizaCardapio(dtoInexistente);
        });

        verify(cardapioRepositoryPort, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Deve lançar NoChangesDetectedException quando não há mudanças")
    void testAtualizaCardapioSemMudancas() {
        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));

        AtualizaCardaptioDto dtoSemMudancas = AtualizaCardaptioDto.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        assertThrows(NoChangesDetectedException.class, () -> {
            atualizaCardapioUseCase.atualizaCardapio(dtoSemMudancas);
        });

        verify(cardapioRepositoryPort, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve atualizar apenas o nome mantendo outros campos")
    void testAtualizaApenasNome() throws Exception {
        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioRepositoryPort.save(any(Cardapio.class))).thenReturn(cardapioAtualizado);
        when(modelMapper.map(cardapioAtualizado, AtualizaCardapioResponse.class)).thenReturn(response);

        AtualizaCardaptioDto dtoNovoNome = AtualizaCardaptioDto.builder()
                .id(1L)
                .nome("Pizza Margherita Premium")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        AtualizaCardapioResponse resultado = atualizaCardapioUseCase.atualizaCardapio(dtoNovoNome);

        assertNotNull(resultado);
        verify(cardapioRepositoryPort, times(1)).save(any(Cardapio.class));
    }

    @Test
    @DisplayName("Deve atualizar apenas o preço mantendo outros campos")
    void testAtualizaApenasPreco() throws Exception {
        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioRepositoryPort.save(any(Cardapio.class))).thenReturn(cardapioAtualizado);
        when(modelMapper.map(cardapioAtualizado, AtualizaCardapioResponse.class)).thenReturn(response);

        AtualizaCardaptioDto dtoNovoPreco = AtualizaCardaptioDto.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("55.00"))
                .disponivel(true)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        AtualizaCardapioResponse resultado = atualizaCardapioUseCase.atualizaCardapio(dtoNovoPreco);

        assertNotNull(resultado);
        verify(cardapioRepositoryPort, times(1)).save(any(Cardapio.class));
    }

    @Test
    @DisplayName("Deve atualizar disponibilidade para false")
    void testAtualizaDisponibilidadeParaFalse() throws Exception {
        Cardapio cardapioIndisponivel = Cardapio.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(false)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioRepositoryPort.save(any(Cardapio.class))).thenReturn(cardapioIndisponivel);

        AtualizaCardapioResponse responseIndisponivel = new AtualizaCardapioResponse();
        responseIndisponivel.setDisponivel(false);
        when(modelMapper.map(cardapioIndisponivel, AtualizaCardapioResponse.class))
                .thenReturn(responseIndisponivel);

        AtualizaCardaptioDto dtoIndisponivel = AtualizaCardaptioDto.builder()
                .id(1L)
                .nome("Pizza Margherita")
                .descricao("Pizza clássica com tomate e mozzarela")
                .preco(new BigDecimal("45.00"))
                .disponivel(false)
                .fotoProduto("https://example.com/pizza.jpg")
                .build();

        AtualizaCardapioResponse resultado = atualizaCardapioUseCase.atualizaCardapio(dtoIndisponivel);

        assertNotNull(resultado);
        verify(cardapioRepositoryPort, times(1)).save(any(Cardapio.class));
    }

    @Test
    @DisplayName("Deve atualizar múltiplos campos simultaneamente")
    void testAtualizaMultiplosCampos() throws Exception {
        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));
        when(cardapioRepositoryPort.save(any(Cardapio.class))).thenReturn(cardapioAtualizado);
        when(modelMapper.map(cardapioAtualizado, AtualizaCardapioResponse.class)).thenReturn(response);

        AtualizaCardapioResponse resultado = atualizaCardapioUseCase.atualizaCardapio(atualizaDto);

        assertNotNull(resultado);
        assertEquals("Pizza Margherita Premium", resultado.getNome());
        assertEquals("Pizza clássica com tomate e mozzarela fresca", resultado.getDescricao());
        assertEquals(new BigDecimal("55.00"), resultado.getPreco());
        verify(cardapioRepositoryPort, times(1)).save(any(Cardapio.class));
    }
}
