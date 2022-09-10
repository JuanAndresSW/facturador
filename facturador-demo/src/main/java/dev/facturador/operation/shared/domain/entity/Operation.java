package dev.facturador.operation.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.invoice.domain.Invoice;
import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Entidad Operacion
 */
@Entity
@Table(name = "operation")
@NoArgsConstructor
@Getter
@Setter
public final class Operation implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_operation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_issuing_trader", nullable = false, updatable = false, referencedColumnName = "id_trader")
    private Trader traderOwner;

    @Column(name = "issuing_point_of_sale_number", nullable = false, length = 3)
    private String issuingPointOfSaleNumber;

    @JsonBackReference
    @OneToMany(mappedBy = "operationProduct", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne(mappedBy = "operationSender", cascade = CascadeType.ALL)
    private Sender sender;

    @OneToOne(mappedBy = "operationReceiver", cascade = CascadeType.ALL)
    private Receiver receiver;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "operation", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Invoice> invoices;


    public Operation(Trader traderOwner, String issuingPointOfSaleNumber) {
        this.traderOwner = traderOwner;
        this.issuingPointOfSaleNumber = issuingPointOfSaleNumber;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "issuingPointOfSaleNumber='" + issuingPointOfSaleNumber + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
