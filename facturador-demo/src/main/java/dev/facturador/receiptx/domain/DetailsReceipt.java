package dev.facturador.receiptx.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "details_receipt")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class DetailsReceipt {

    @Id
    @Column(name = "id_details_receipt")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceiptDetails;

    @Column(name = "num", nullable = false)
    private int num;

    @Column(name = "deposit_date", nullable = false)
    private LocalDate depositDate;

    @Column(name = "amount", nullable = false, scale = 2)
    private double amountReceipt;

    @Column(name = "kind_of_value", nullable = false, length = 255)
    private String typeValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_receipt_x", nullable = false)
    private ReceiptX receiptX;


}