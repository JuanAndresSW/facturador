package dev.facturador.operation.wholeoperation.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.wholeoperation.application.WholeOperation;
import dev.facturador.operation.wholeoperation.domain.SellConditions;
import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.shared.domain.entity.Operation;
import dev.facturador.operation.shared.domain.entity.Product;
import dev.facturador.operation.shared.domain.entity.Receiver;
import dev.facturador.operation.shared.domain.entity.Sender;
import dev.facturador.operation.wholeoperation.domain.model.DataReququiredOperation;
import dev.facturador.operation.wholeoperation.domain.model.WholeOperationRestModel;
import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dev.facturador.operation.wholeoperation.domain.SellConditions.defineSellCondition;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_operation_parent", nullable = false, updatable = false, referencedColumnName = "id_operation", unique = true)
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

    public static CreditNote create(WholeOperationRestModel values, DataReququiredOperation internalValues) {
        var invoice = new CreditNote();
        //Basic data
        invoice.setSellConditions(defineSellCondition(values.getSellConditions()));
        invoice.setVat(values.getVat());
        //Numero
        invoice.setOperationNumberCount(internalValues.getOperationNumberCount());
        invoice.setCreditNumber(internalValues.getOperationNumber());

        //Crear operacion
        invoice.setOperation(new Operation(
                new Trader(values.getIDTrader()), internalValues.getPointOfSaleNumber()));
        //Crear receiver
        invoice.getOperation().setReceiver(new Receiver());
        invoice.getOperation().getReceiver().setReceiverCode(values.getReceiverCode());
        invoice.getOperation().getReceiver().setReceiverName(values.getReceiverName());
        invoice.getOperation().getReceiver().setReceiverLocality(values.getReceiverLocality());
        invoice.getOperation().getReceiver().setReceiverPostalCode(values.getReceiverPostalCode());
        invoice.getOperation().getReceiver().setReceiverVatCategory(defineAllVat(values.getReceiverVatCategory()));
        invoice.getOperation().getReceiver().setReceiverAddress(values.getReceiverAddress());
        invoice.getOperation().getReceiver().setOperationReceiver(invoice.getOperation());
        //Crear Sender
        invoice.getOperation().setSender(new Sender());
        invoice.getOperation().getSender().setSenderCode(internalValues.getSenderCuit());
        invoice.getOperation().getSender().setSenderName(internalValues.getSenderName());
        invoice.getOperation().getSender().setSenderContact(internalValues.getSenderPhone());
        invoice.getOperation().getSender().setSenderVatCategory(internalValues.getVatCategory());
        invoice.getOperation().getSender().setSenderAddress(
                internalValues.getSenderStreet().concat(" ").concat(internalValues.getSenderAddressNumber()));
        invoice.getOperation().getSender().setOperationSender(invoice.getOperation());
        //Crear productos
        List<Product> lista = new ArrayList<>();

        values.getProducts().forEach(x -> lista.add(
                new Product(x.getQuantity(), x.getPrice(), x.getDetail(), invoice.getOperation())));
        invoice.getOperation().setProducts(lista);

        //Definir tipo de factura
        invoice.setType(internalValues.getType());
        //Fecha de creacion
        invoice.setIssueDate(LocalDate.now());
        return invoice;
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
