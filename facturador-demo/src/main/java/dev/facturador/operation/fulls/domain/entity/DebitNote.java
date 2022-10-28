package dev.facturador.operation.fulls.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.fulls.domain.SellConditions;
import dev.facturador.operation.core.domain.DocumentType;
import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.operation.core.domain.entity.Product;
import dev.facturador.operation.core.domain.entity.Receiver;
import dev.facturador.operation.core.domain.entity.Sender;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import dev.facturador.operation.fulls.domain.model.FullOperationRestModel;
import dev.facturador.trader.domain.Trader;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dev.facturador.operation.fulls.domain.SellConditions.defineSellCondition;
import static dev.facturador.operation.core.domain.AllVatCategory.defineAllVat;

/**
 * Entidad Factura
 */
@Entity
@Table(name = "debit_note")
@EqualsAndHashCode
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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false,
            columnDefinition = "enum('A','B','C')")
    private DocumentType type;

    @JsonIgnore
    @Column(name = "count_debit_number", nullable = false, length = 8)
    private Integer operationNumberCount;

    @Column(name = "debit_number", nullable = false)
    private String debitNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_operation_parent", nullable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operation;

    public DebitNote(Operation operation) {
        this.operation = operation;
    }

    public DebitNote(Long debitId,
                     SellConditions sellConditions,
                     Integer vat,
                     DocumentType type,
                     Integer operationNumberCount,
                     String debitNumber,
                     Operation operation) {
        this.debitId = debitId;
        this.sellConditions = sellConditions;
        this.vat = vat;
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
        debitNote.getOperation().getReceiver().setReceiverCity(values.getReceiverCity());
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
        debitNote.getOperation().setIssueDate(LocalDate.now());
        return debitNote;
    }

    @Override
    public String toString() {
        return "DebitNote{" +
                "sellConditions=" + sellConditions +
                ", vat=" + vat +
                ", type=" + type +
                ", posNumberInvoice=" + operationNumberCount +
                ", invoiceNumber=" + debitNumber +
                '}';
    }
}
