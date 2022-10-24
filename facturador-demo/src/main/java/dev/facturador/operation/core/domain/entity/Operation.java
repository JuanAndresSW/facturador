package dev.facturador.operation.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.fulls.domain.entity.CreditNote;
import dev.facturador.operation.fulls.domain.entity.DebitNote;
import dev.facturador.operation.fulls.domain.entity.Invoice;
import dev.facturador.operation.remittance.domain.Remittance;
import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "issuing_point_of_sale_number", nullable = false, length = 3)
    private String issuingPointOfSaleNumber;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @JsonBackReference
    @OneToMany(mappedBy = "operationProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    @JsonIgnore
    @OneToOne(mappedBy = "operationSender", cascade = CascadeType.ALL)
    private Sender sender;

    @JsonIgnore
    @OneToOne(mappedBy = "operationReceiver", cascade = CascadeType.ALL)
    private Receiver receiver;

    @OneToOne(mappedBy = "operation", cascade = CascadeType.ALL)
    private Invoice invoice;

    @OneToOne(mappedBy = "operation", cascade = CascadeType.ALL)
    private DebitNote debitNote;

    @OneToOne(mappedBy = "operation", cascade = CascadeType.ALL)
    private CreditNote creditNote;

    @OneToOne(mappedBy = "operation", cascade = CascadeType.ALL)
    private Remittance remittance;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_issuing_trader", nullable = false, updatable = false, referencedColumnName = "id_trader")
    private Trader traderOwner;

    public Operation(Long operationId) {
        this.operationId = operationId;
    }

    public Operation(Trader traderOwner, String issuingPointOfSaleNumber) {
        this.traderOwner = traderOwner;
        this.issuingPointOfSaleNumber = issuingPointOfSaleNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        return new EqualsBuilder().append(getTraderOwner(), operation.getTraderOwner()).append(getSender(), operation.getSender()).append(getReceiver(), operation.getReceiver()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getTraderOwner()).append(getSender()).append(getReceiver()).toHashCode();
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationId=" + operationId +
                ", issuingPointOfSaleNumber='" + issuingPointOfSaleNumber + '\'' +
                ", issueDate=" + issueDate +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", invoice=" + invoice +
                ", debitNote=" + debitNote +
                ", creditNote=" + creditNote +
                ", remittance=" + remittance +
                '}';
    }
}
