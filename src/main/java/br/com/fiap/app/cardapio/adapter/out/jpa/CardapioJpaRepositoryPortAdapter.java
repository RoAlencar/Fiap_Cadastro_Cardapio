package br.com.fiap.app.cardapio.adapter.out.jpa;

import br.com.fiap.app.cardapio.adapter.out.jpa.cardapio.entities.CardapioEntity;
import br.com.fiap.app.cardapio.adapter.out.jpa.cardapio.repositories.CardapioJpaRepository;
import br.com.fiap.app.cardapio.application.port.CardapioRepositoryPort;
import br.com.fiap.app.cardapio.domain.Cardapio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardapioJpaRepositoryPortAdapter implements CardapioRepositoryPort {

    private final CardapioJpaRepository cardapioJpaRepository;

    @Override
    public List<Cardapio> findAll() {
        return cardapioJpaRepository.findAll().stream().map(CardapioEntity::toDomain).toList();
    }

    @Override
    public List<Cardapio> findByNomeLikeIgnoreCaseAndAccent(String nome) {
        return cardapioJpaRepository.findByNomeLikeIgnoreCaseAndAccent(nome).stream()
                .filter(Optional::isPresent)
                .map(optionalEntity -> optionalEntity.get().toDomain())
                .toList();
    }

    @Override
    public Optional<Cardapio> findById(Long id) {
        return cardapioJpaRepository.findById(id).map(CardapioEntity::toDomain);
    }

    @Override
    public Cardapio save(Cardapio cardapio) {
        return cardapioJpaRepository.save(cardapio.toEntity()).toDomain();
    }

    @Override
    public void deletaById(Long id) {
        cardapioJpaRepository.deleteById(id);
    }
}
