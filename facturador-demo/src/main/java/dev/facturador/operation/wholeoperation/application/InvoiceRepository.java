package dev.facturador.operation.wholeoperation.application;

import dev.facturador.operation.wholeoperation.domain.entity.Invoice;
import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.wholeoperation.domain.model.OperationCount;
import dev.facturador.trader.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT new dev.facturador.operation.wholeoperation.domain.model.OperationCount(MAX(operationNumberCount)) FROM dev.facturador.operation.wholeoperation.domain.entity.Invoice i WHERE i.type = :type AND i.operation.traderOwner = :trader AND i.operation.issuingPointOfSaleNumber = :number")
    @Transactional(readOnly = true)
    Optional<OperationCount> findOperationNumberCount
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);


    @Query(value = "SELECT new dev.facturador.operation.wholeoperation.domain.entity.Invoice(invoiceId, sellConditions, vat, issueDate, type, operationNumberCount, invoiceNumber, operation) FROM dev.facturador.operation.wholeoperation.domain.entity.Invoice i WHERE i.type = :type AND i.operation.traderOwner = :trader AND i.invoiceNumber = :number")
    Invoice findInvoice
            (@Param("type") DocumentType type, @Param("trader") Trader trader, @Param("number") String number);

}




/*
    @Query(value = "SELECT i.posNumberInvoice, MAX(i.invoiceNumber) FROM dev.facturador.operation.invoice.domain.DebitNote i WHERE i.type = :type AND i.operation.traderOwner = :trader AND i.operation.issuingPointOfSaleNumber = :number")
    @Query("SELECT * FROM Tutorial t WHERE t.title LIKE %?1%")
    @Query("SELECT t FROM Tutorial t WHERE t.createdAt BETWEEN ?1 AND ?2")
    @Query("SELECT t FROM Tutorial t ORDER BY t.level DESC")
    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%')) ORDER BY t.level ASC")
    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
           (Agregando Sort en los parametros)


     @Query("SELECT t FROM Tutorial t WHERE " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    List<Todo> findBySearchTerm(@Param("searchTerm") String searchTerm);

    Optional<T> findById(Predicate predicate);
*/
