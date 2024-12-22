package dev.facturador.operation.fulls.application;

import dev.facturador.operation.fulls.domain.entity.Invoice;
import dev.facturador.operation.core.domain.DocumentType;
import dev.facturador.operation.fulls.domain.model.OperationCount;
import dev.facturador.trader.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT new dev.facturador.operation.fulls.domain.model.OperationCount(MAX(operationNumberCount)) FROM dev.facturador.operation.fulls.domain.entity.Invoice i WHERE i.type = :type AND i.operation.traderOwner = :trader AND i.operation.issuingPointOfSaleNumber = :number")
    @Transactional(readOnly = true)
    Optional<OperationCount> findOperationNumberCount
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);


    @Query(value = "SELECT new dev.facturador.operation.fulls.domain.entity.Invoice(invoiceId, sellConditions, vat, type, operationNumberCount, invoiceNumber, operation) FROM dev.facturador.operation.fulls.domain.entity.Invoice i WHERE i.type = :type AND i.operation.traderOwner = :trader AND i.invoiceNumber = :number")
    Invoice findInvoice
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);

}
