package dev.facturador.operation.invoice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.shared.domain.entity.Operation;
import dev.facturador.operation.shared.domain.entity.Product;
import dev.facturador.operation.shared.domain.entity.Receiver;
import dev.facturador.operation.shared.domain.entity.Sender;
import dev.facturador.operation.shared.domain.model.WholeOperationRestModel;
import dev.facturador.operation.shared.domain.model.DataReququiredOperation;
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

import static dev.facturador.operation.invoice.domain.SellConditions.defineSellCondition;
import static dev.facturador.operation.shared.domain.AllVatCategory.defineAllVat;

/**
 * Entidad Factura
 */
@Slf4j
@Entity
@Table(name = "invoice")
@NoArgsConstructor
@Getter
@Setter
public final class Invoice implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_invoice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;


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


    @Column(name = "count_invoice_number", nullable = false, length = 8)
    private Integer operationNumberCount;

    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_operation_parent", nullable = false, updatable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operation;

    public Invoice(Long invoiceId,
                   SellConditions sellConditions,
                   Integer vat,
                   LocalDate issueDate,
                   DocumentType type,
                   Integer operationNumberCount,
                   String invoiceNumber,
                   Operation operation) {
        this.invoiceId = invoiceId;
        this.sellConditions = sellConditions;
        this.vat = vat;
        this.issueDate = issueDate;
        this.type = type;
        this.operationNumberCount = operationNumberCount;
        this.invoiceNumber = invoiceNumber;
        this.operation = operation;
    }

    public Invoice(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public static Invoice create(WholeOperationRestModel values, DataReququiredOperation internalValues) {
        var invoice = new Invoice();
        //Basic data
        invoice.setSellConditions(defineSellCondition(values.getSellConditions()));
        invoice.setVat(values.getVat());
        //Numero
        invoice.setOperationNumberCount(internalValues.getOperationNumberCount());
        invoice.setInvoiceNumber(internalValues.getOperationNumber());

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
        return "Invoice{" +
                "sellConditions=" + sellConditions +
                ", vat=" + vat +
                ", issueDate=" + issueDate +
                ", type=" + type +
                ", posNumberInvoice=" + operationNumberCount +
                ", invoiceNumber=" + invoiceNumber +
                ", operation=" + operation +
                '}';
    }
}
