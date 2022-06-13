package dev.facturador.notused.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "receipt_x")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class ReceiptX {

    @Id
    @Column(name = "id_receipt_x")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceiptX;

    @Column(name = "num_receipt_x", nullable = false)
    private int numReceiptX;

    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing", nullable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Operation dataOperation;

    @Column(name = "cash", nullable = false, scale = 2)
    private double payCash;

    @Column(name = "documents", nullable = false, scale = 2)
    private double payDocuments;

    @Column(name = "check", nullable = false, scale = 2)
    private double payCheck;

    @Column(name = "time", nullable = false)
    private LocalTime horary;

    @Column(name = "payer", nullable = false, length = 40)
    private String payerReceiptX;

    @Column(name = "payer_address", nullable = false, length = 20)
    private String paymentAddress;
}