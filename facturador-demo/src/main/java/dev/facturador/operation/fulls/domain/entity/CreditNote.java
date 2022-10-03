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
@Slf4j
@Entity
@Table(name = "credit_note")
@NoArgsConstructor
@Getter
@Setter
public final class CreditNote implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_credit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditId;

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


    @Column(name = "count_credit_number", nullable = false, length = 8)
    private Integer operationNumberCount;

    @Column(name = "credit_number", nullable = false)
    private String creditNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_operation_parent", nullable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operation;

    public CreditNote(Long creditId,
                      SellConditions sellConditions,
                      Integer vat,
                      LocalDate issueDate,
                      DocumentType type,
                      Integer operationNumberCount,
                      String creditNumber,
                      Operation operation) {
        this.creditId = creditId;
        this.sellConditions = sellConditions;
        this.vat = vat;
        this.issueDate = issueDate;
        this.type = type;
        this.operationNumberCount = operationNumberCount;
        this.creditNumber = creditNumber;
        this.operation = operation;
    }

    public CreditNote(Long creditId) {
        this.creditId = creditId;
    }

    public static CreditNote create(FullOperationRestModel values, DataRequiredOperation internalValues) {
        var creditNote = new CreditNote();
        //Condicion de venta
        creditNote.setSellConditions(defineSellCondition(values.getSellConditions()));
        //Definir IVA
        creditNote.setVat(values.getVat());
        //Numero
        creditNote.setOperationNumberCount(internalValues.getOperationNumberCount());
        creditNote.setCreditNumber(internalValues.getOperationNumber());

        //Crear operacion
        creditNote.setOperation(new Operation(
                new Trader(values.getIDTrader()), internalValues.getPointOfSaleNumber()));
        //Crear receiver
        creditNote.getOperation().setReceiver(new Receiver());
        creditNote.getOperation().getReceiver().setReceiverCode(values.getReceiverCode());
        creditNote.getOperation().getReceiver().setReceiverName(values.getReceiverName());
        creditNote.getOperation().getReceiver().setReceiverLocality(values.getReceiverLocality());
        creditNote.getOperation().getReceiver().setReceiverPostalCode(values.getReceiverPostalCode());
        creditNote.getOperation().getReceiver().setReceiverVatCategory(defineAllVat(values.getReceiverVatCategory()));
        creditNote.getOperation().getReceiver().setReceiverAddress(values.getReceiverAddress());
        creditNote.getOperation().getReceiver().setOperationReceiver(creditNote.getOperation());
        //Crear Sender
        creditNote.getOperation().setSender(new Sender());
        creditNote.getOperation().getSender().setSenderCode(internalValues.getSenderCuit());
        creditNote.getOperation().getSender().setSenderName(internalValues.getSenderName());
        creditNote.getOperation().getSender().setSenderContact(internalValues.getSenderPhone());
        creditNote.getOperation().getSender().setSenderVatCategory(internalValues.getVatCategory());
        creditNote.getOperation().getSender().setSenderAddress(
                internalValues.getSenderStreet().concat(" ").concat(internalValues.getSenderAddressNumber()));
        creditNote.getOperation().getSender().setOperationSender(creditNote.getOperation());
        //Crear productos
        List<Product> lista = new ArrayList<>();

        values.getProducts().forEach(x -> lista.add(
                new Product(x.getQuantity(), x.getPrice(), x.getDetail(), creditNote.getOperation())));
        creditNote.getOperation().setProducts(lista);

        //Definir tipo de factura
        creditNote.setType(internalValues.getType());
        //Fecha de creacion
        creditNote.setIssueDate(LocalDate.now());
        return creditNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CreditNote that = (CreditNote) o;

        return new EqualsBuilder().append(getSellConditions(), that.getSellConditions()).append(getVat(), that.getVat()).append(getType(), that.getType()).append(getOperationNumberCount(), that.getOperationNumberCount()).append(getCreditNumber(), that.getCreditNumber()).append(getOperation(), that.getOperation()).isEquals();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getSellConditions()).append(getVat()).append(getType()).append(getOperationNumberCount()).append(getCreditNumber()).append(getOperation()).toHashCode();
    }

    @Override
    public String toString() {
        return "DebitNote{" +
                "sellConditions=" + sellConditions +
                ", vat=" + vat +
                ", issueDate=" + issueDate +
                ", type=" + type +
                ", posNumberInvoice=" + operationNumberCount +
                ", invoiceNumber=" + creditNumber +
                ", operation=" + operation +
                '}';
    }
}
