package br.com.fiap.app.cardapio.application.usecase;

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

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeletaCardapioUseCaseTest {

    @Mock
    private CardapioRepositoryPort cardapioRepositoryPort;

    @InjectMocks
    private DeletaCardapioUseCase deletaCardapioUseCase;

    private Cardapio cardapioExistente;

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
    }

    @Test
    @DisplayName("Deve deletar um cardápio com sucesso")
    void testDeletaCardapioComSucesso() throws CardapioNotFoundException {
        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));
        doNothing().when(cardapioRepositoryPort).deletaById(1L);

        deletaCardapioUseCase.deletaCardapio(1L);

        verify(cardapioRepositoryPort, times(1)).findById(1L);
        verify(cardapioRepositoryPort, times(1)).deletaById(1L);
    }

    @Test
    @DisplayName("Deve lançar CardapioNotFoundException quando cardápio não existe")
    void testDeletaCardapioNaoEncontrado() {
        when(cardapioRepositoryPort.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CardapioNotFoundException.class, () -> {
            deletaCardapioUseCase.deletaCardapio(999L);
        });

        verify(cardapioRepositoryPort, times(1)).findById(999L);
        verify(cardapioRepositoryPort, times(0)).deletaById(999L);
    }

    @Test
    @DisplayName("Deve lançar CardapioNotFoundException com id negativo")
    void testDeletaCardapioComIdNegativo() {
        when(cardapioRepositoryPort.findById(-1L)).thenReturn(Optional.empty());

        assertThrows(CardapioNotFoundException.class, () -> {
            deletaCardapioUseCase.deletaCardapio(-1L);
        });

        verify(cardapioRepositoryPort, times(1)).findById(-1L);
    }

    @Test
    @DisplayName("Deve lançar CardapioNotFoundException com id zero")
    void testDeletaCardapioComIdZero() {
        when(cardapioRepositoryPort.findById(0L)).thenReturn(Optional.empty());

        assertThrows(CardapioNotFoundException.class, () -> {
            deletaCardapioUseCase.deletaCardapio(0L);
        });

        verify(cardapioRepositoryPort, times(1)).findById(0L);
    }

    @Test
    @DisplayName("Deve buscar antes de deletar para validar existência")
    void testBuscaAntesDeDeletar() throws CardapioNotFoundException {
        when(cardapioRepositoryPort.findById(1L)).thenReturn(Optional.of(cardapioExistente));
        doNothing().when(cardapioRepositoryPort).deletaById(1L);

        deletaCardapioUseCase.deletaCardapio(1L);

        verify(cardapioRepositoryPort, times(1)).findById(1L);
        verify(cardapioRepositoryPort, times(1)).deletaById(cardapioExistente.getId());
    }

    @Test
    @DisplayName("Deve deletar cardápio correto pelo ID")
    void testDeletaCardapioCorreto() throws CardapioNotFoundException {
        Long idParaDeletar = 5L;
        Cardapio cardapioParaDeletar = Cardapio.builder()
                .id(idParaDeletar)
                .nome("Hambúrguer")
                .preco(new BigDecimal("30.00"))
                .build();

        when(cardapioRepositoryPort.findById(idParaDeletar)).thenReturn(Optional.of(cardapioParaDeletar));
        doNothing().when(cardapioRepositoryPort).deletaById(idParaDeletar);

        deletaCardapioUseCase.deletaCardapio(idParaDeletar);

        verify(cardapioRepositoryPort, times(1)).findById(idParaDeletar);
        verify(cardapioRepositoryPort, times(1)).deletaById(idParaDeletar);
    }
}
