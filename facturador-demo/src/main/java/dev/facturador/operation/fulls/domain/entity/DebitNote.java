package dev.facturador.operation.fulls.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.fulls.domain.SellConditions;
import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.shared.domain.entity.Operation;
import dev.facturador.operation.shared.domain.entity.Product;
import dev.facturador.operation.shared.domain.entity.Receiver;
import dev.facturador.operation.shared.domain.entity.Sender;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import dev.facturador.operation.fulls.domain.model.FullOperationRestModel;
import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dev.facturador.operation.fulls.domain.SellConditions.defineSellCondition;
import static dev.facturador.operation.shared.domain.AllVatCategory.defineAllVat;

/**
 * Entidad Factura
 */
@Entity
@Table(name = "debit_note")
@NoArgsConstructor
@Getter
@Setter
public final class DebitNote implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_debit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long debitId;


    @Enumerated(value = EnumType.STRING)
    @Column(name = "sell_conditions", nullable = false,
            columnDefinition = "enum('CASH','CARD','CHECKING_ACCOUNT','DOCUMENT')")
    private SellConditions sellConditions;

    @Column(nullable = false)
    private Integer vat;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false,
            columnDefinition = "enum('A','B','C')")
    private DocumentType type;


    @Column(name = "count_debit_number", nullable = false, length = 8)
    private Integer operationNumberCount;

    @Column(name = "debit_number", nullable = false)
    private String debitNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_operation_parent", nullable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operation;

    public DebitNote(Long debitId,
                     SellConditions sellConditions,
                     Integer vat,
                     LocalDate issueDate,
                     DocumentType type,
                     Integer operationNumberCount,
                     String debitNumber,
                     Operation operation) {
        this.debitId = debitId;
        this.sellConditions = sellConditions;
        this.vat = vat;
        this.issueDate = issueDate;
        this.type = type;
        this.operationNumberCount = operationNumberCount;
        this.debitNumber = debitNumber;
        this.operation = operation;
    }

    public DebitNote(Long debitId) {
        this.debitId = debitId;
    }

    public static DebitNote create(FullOperationRestModel values, DataRequiredOperation internalValues) {
        var debitNote = new DebitNote();
        //Condicion de venta
        debitNote.setSellConditions(defineSellCondition(values.getSellConditions()));
        //Definir IVA
        debitNote.setVat(values.getVat());
        //Numero
        debitNote.setOperationNumberCount(internalValues.getOperationNumberCount());
        debitNote.setDebitNumber(internalValues.getOperationNumber());

        //Crear operacion
        debitNote.setOperation(new Operation(
                new Trader(values.getIDTrader()), internalValues.getPointOfSaleNumber()));
        //Crear receiver
        debitNote.getOperation().setReceiver(new Receiver());
        debitNote.getOperation().getReceiver().setReceiverCode(values.getReceiverCode());
        debitNote.getOperation().getReceiver().setReceiverName(values.getReceiverName());
        debitNote.getOperation().getReceiver().setReceiverLocality(values.getReceiverLocality());
        debitNote.getOperation().getReceiver().setReceiverPostalCode(values.getReceiverPostalCode());
        debitNote.getOperation().getReceiver().setReceiverVatCategory(defineAllVat(values.getReceiverVatCategory()));
        debitNote.getOperation().getReceiver().setReceiverAddress(values.getReceiverAddress());
        debitNote.getOperation().getReceiver().setOperationReceiver(debitNote.getOperation());
        //Crear Sender
        debitNote.getOperation().setSender(new Sender());
        debitNote.getOperation().getSender().setSenderCode(internalValues.getSenderCuit());
        debitNote.getOperation().getSender().setSenderName(internalValues.getSenderName());
        debitNote.getOperation().getSender().setSenderContact(internalValues.getSenderPhone());
        debitNote.getOperation().getSender().setSenderVatCategory(internalValues.getVatCategory());
        debitNote.getOperation().getSender().setSenderAddress(
                internalValues.getSenderStreet().concat(" ").concat(internalValues.getSenderAddressNumber()));
        debitNote.getOperation().getSender().setOperationSender(debitNote.getOperation());
        //Crear productos
        List<Product> lista = new ArrayList<>();

        values.getProducts().forEach(x -> lista.add(
                new Product(x.getQuantity(), x.getPrice(), x.getDetail(), debitNote.getOperation())));
        debitNote.getOperation().setProducts(lista);

        //Definir tipo de factura
        debitNote.setType(internalValues.getType());
        //Fecha de creacion
        debitNote.setIssueDate(LocalDate.now());
        return debitNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DebitNote debitNote = (DebitNote) o;

        return new EqualsBuilder().append(getSellConditions(), debitNote.getSellConditions()).append(getVat(), debitNote.getVat()).append(getType(), debitNote.getType()).append(getOperation(), debitNote.getOperation()).isEquals();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getSellConditions()).append(getVat()).append(getType()).append(getOperation()).toHashCode();
    }

    @Override
    public String toString() {
        return "DebitNote{" +
                "sellConditions=" + sellConditions +
                ", vat=" + vat +
                ", issueDate=" + issueDate +
                ", type=" + type +
                ", posNumberInvoice=" + operationNumberCount +
                ", invoiceNumber=" + debitNumber +
                ", operation=" + operation +
                '}';
    }
}
