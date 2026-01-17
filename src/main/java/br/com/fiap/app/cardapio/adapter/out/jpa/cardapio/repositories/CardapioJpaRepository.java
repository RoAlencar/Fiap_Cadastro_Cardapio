package br.com.fiap.app.cardapio.adapter.out.jpa.cardapio.repositories;

import br.com.fiap.app.cardapio.adapter.out.jpa.cardapio.entities.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardapioJpaRepository extends JpaRepository<CardapioEntity, Long> {

    @Query(value = "SELECT * FROM core_cardapio WHERE unaccent(LOWER(nome)) LIKE unaccent(LOWER(CONCAT('%', :nome, '%')))", nativeQuery = true)
    List<Optional<CardapioEntity>> findByNomeLikeIgnoreCaseAndAccent(String nome);
}
