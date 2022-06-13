package dev.facturador.notused.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "purchase_order")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class PurchaseOrder {

    @Id
    @Column(name = "id_purchase_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPurchaseOrder;

    @Column(name = "num_purchase_order", nullable = false)
    private int purchaseOrderNum;

    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing", nullable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Operation dataOperation;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "place_of_delivery", nullable = false, length = 20)
    private String dispatchPlace;

    @Column(name = "carrier", nullable = false, length = 25)
    private String carrierName;

    @Column(name = "conditions", nullable = false, length = 20)
    private String conditions;
}