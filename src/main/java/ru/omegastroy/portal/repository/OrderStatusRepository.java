package ru.omegastroy.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omegastroy.portal.model.Basket;
import ru.omegastroy.portal.model.OrderStatusCatalog;

import java.util.Optional;
import java.util.UUID;

public interface OrderStatusRepository extends JpaRepository<OrderStatusCatalog, Integer> {
    Optional<OrderStatusCatalog> findByCode(String code);
}
