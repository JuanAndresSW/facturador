package dev.facturador.operation.shared.application;

import dev.facturador.operation.shared.domain.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
