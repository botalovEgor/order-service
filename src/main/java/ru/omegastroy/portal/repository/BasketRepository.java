package ru.omegastroy.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omegastroy.portal.model.Basket;

import java.util.Optional;
import java.util.UUID;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByClientUuid(UUID clientUuid);
}
