package dev.facturador.operation.wholeoperation.application;

import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.wholeoperation.domain.model.OperationCount;
import dev.facturador.operation.wholeoperation.domain.entity.DebitNote;
import dev.facturador.trader.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DebitNoteRepository extends JpaRepository<DebitNote, Long> {

    @Query(value = "SELECT new dev.facturador.operation.wholeoperation.domain.model.OperationCount(MAX(operationNumberCount)) FROM dev.facturador.operation.wholeoperation.domain.entity.DebitNote d WHERE d.type = :type AND d.operation.traderOwner = :trader AND d.operation.issuingPointOfSaleNumber = :number")
    @Transactional(readOnly = true)
    Optional<OperationCount> findOperationNumberCount
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);

    @Query(value = "SELECT new dev.facturador.operation.wholeoperation.domain.entity.DebitNote(debitId, sellConditions, vat, issueDate, type, operationNumberCount, debitNumber, operation) FROM dev.facturador.operation.wholeoperation.domain.entity.DebitNote d WHERE d.type = :type AND d.operation.traderOwner = :trader AND d.debitNumber = :number")
    DebitNote findDebitNote
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);

}
