package dev.facturador.operation.core.application;

import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.trader.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByTraderOwner(Trader trader);
    Optional<Operation> findByOperationId(Long id);
}
