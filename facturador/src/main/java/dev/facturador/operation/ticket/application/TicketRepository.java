package dev.facturador.operation.ticket.application;

import dev.facturador.operation.fulls.domain.model.OperationCount;
import dev.facturador.operation.ticket.domain.Ticket;
import dev.facturador.trader.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT new dev.facturador.operation.fulls.domain.model.OperationCount(MAX(operationNumberCount)) FROM dev.facturador.operation.ticket.domain.Ticket i WHERE i.operation.traderOwner = :trader AND i.operation.issuingPointOfSaleNumber = :number")
    @Transactional(readOnly = true)
    Optional<OperationCount> findOperationNumberCount
            (@Param("trader") Trader trader, @Param("number") String number);

}
