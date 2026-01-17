package br.com.fiap.app.cardapio.application.port;

import br.com.fiap.app.cardapio.domain.Cardapio;

import java.util.List;
import java.util.Optional;

public interface CardapioRepositoryPort {

    List<Cardapio> findAll();

    List<Cardapio> findByNomeLikeIgnoreCaseAndAccent (String nome);

    Optional<Cardapio> findById(Long id);

    Cardapio save(Cardapio cardapio);

    void deletaById(Long id);
}
