package br.com.fiap.app.cardapio.infrastructure.exception;

import br.com.fiap.app.cardapio.infrastructure.exception.custom.AvailabilityIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.CardapioNotFoundException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.DescriptionIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NameIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PhotoOfTheProductIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.PriceIsRequiredException;
import br.com.fiap.app.cardapio.infrastructure.exception.custom.RequestRequiredException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void handlerAvailabilityIsRequiredException() {
        AvailabilityIsRequiredException availabilityIsRequiredException = new AvailabilityIsRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleAvailabilityIsRequiredException(availabilityIsRequiredException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("A informação de disponibilidade do produto é obrigatória", response.getBody().getDetail());
    }

    @Test
    public void handlerCardapioNotFoundException() {
        CardapioNotFoundException cardapioNotFoundException = new CardapioNotFoundException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleCardapioNotFoundException(cardapioNotFoundException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("O Cardapio não foi encontrado", response.getBody().getDetail());
    }

    @Test
    public void handlerDescriptionIsRequiredException(){
        DescriptionIsRequiredException descriptionIsRequiredException = new DescriptionIsRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleDescriptionIsRequiredException(descriptionIsRequiredException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("A descrição do produto é obrigatória", response.getBody().getDetail());
    }

    @Test
    public void handlerNameIsRequiredException(){
        NameIsRequiredException nameIsRequiredException = new NameIsRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleNameIsRequiredException(nameIsRequiredException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("O nome do produto é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handlerNoChangesDetectedException(){
        NoChangesDetectedException noChangesDetectedException = new NoChangesDetectedException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleNoChangesDetectedException(noChangesDetectedException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("Não foram detectadas alterações nos dados do Cardapio", response.getBody().getDetail());
    }

    @Test
    public void handlerPhotoOfTheProductIsRequiredException(){
        PhotoOfTheProductIsRequiredException photoOfTheProductIsRequiredException = new PhotoOfTheProductIsRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handlePhotoOfTheProductIsRequiredException(photoOfTheProductIsRequiredException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("A foto do produto é obrigatória", response.getBody().getDetail());
    }

    @Test
    public void handlerPriceIsRequiredException(){
        PriceIsRequiredException priceIsRequiredException = new PriceIsRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handlePriceIsRequiredException(priceIsRequiredException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("O preço do produto é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handlerRequestRequiredException(){
        RequestRequiredException requestRequiredException = new RequestRequiredException();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/cardapio");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleRequestRequiredException(requestRequiredException, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assert response.getBody() != null;
        assertEquals("A Requisição é obrigatória", response.getBody().getDetail());
    }
}
