package dev.facturador.operation.fulls.application;

import dev.facturador.operation.core.domain.DocumentType;
import dev.facturador.operation.fulls.domain.model.OperationCount;
import dev.facturador.operation.fulls.domain.entity.CreditNote;
import dev.facturador.trader.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CreditNoteRepository extends JpaRepository<CreditNote, Long> {

    @Query(value = "SELECT new dev.facturador.operation.fulls.domain.model.OperationCount(MAX(operationNumberCount)) FROM dev.facturador.operation.fulls.domain.entity.CreditNote c WHERE c.type = :type AND c.operation.traderOwner = :trader AND c.operation.issuingPointOfSaleNumber = :number")
    @Transactional(readOnly = true)
    Optional<OperationCount> findOperationNumberCount
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);

    @Query(value = "SELECT new dev.facturador.operation.fulls.domain.entity.CreditNote(creditId, sellConditions, vat, type, operationNumberCount, creditNumber, operation) FROM dev.facturador.operation.fulls.domain.entity.CreditNote c WHERE c.type = :type AND c.operation.traderOwner = :trader AND c.creditNumber = :number")
    CreditNote findCreditNote
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);

}
