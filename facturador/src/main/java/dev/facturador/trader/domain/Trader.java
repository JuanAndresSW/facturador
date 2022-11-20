package dev.facturador.trader.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.branch.domain.Branch;
import dev.facturador.global.domain.VatCategory;
import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
@Entity
@Table(name = "trader")
@NoArgsConstructor
@Getter
@Setter
public final class Trader implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_trader")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long traderId;
    @Column(nullable = false, length = 15, updatable = false, unique = true)
    private String cuit;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "vat_category", nullable = false,
            columnDefinition = "enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE')")
    private VatCategory vatCategory;
    @Column(name = "business_name", nullable = false, length = 20)
    private String businessName;

    @OneToOne(mappedBy = "trader", cascade = CascadeType.ALL)
    private PointsOfSaleControl pointsOfSaleControl;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "traderOwner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Branch> branches;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "traderOwner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Operation> operations;

    public Trader(long traderId) {
        this.traderId = traderId;
    }

    public Trader(long traderId, String cuit, String businessName) {
        this.traderId = traderId;
        this.cuit = cuit;
        this.businessName = businessName;
    }

    public Trader(String cuit, String businessName) {
        this.cuit = cuit;
        this.businessName = businessName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Trader trader = (Trader) o;

        return new EqualsBuilder().append(getTraderId(), trader.getTraderId()).isEquals();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getTraderId()).toHashCode();
    }

    @Override
    public String toString() {
        return "Trader{" +
                "idTrader=" + traderId +
                ", cuit='" + cuit + '\'' +
                ", vatCategory=" + vatCategory.vatToLowercaseAndSpanish() +
                ", name='" + businessName + '\'' +
                '}';
    }
}
