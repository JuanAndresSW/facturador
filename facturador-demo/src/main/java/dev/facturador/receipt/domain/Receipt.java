package dev.facturador.receipt.domain;

import dev.facturador.operation.domain.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "recibo")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Receipt {

    @Id
    @Column(name = "id_receipt")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceipt;

    @Column(name = "num_receipt", nullable = false)
    private int receiptNum;

    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing", nullable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Operation dataOperation;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "address", nullable = false, length = 40)
    private String address;

    @Column(name = "payer", nullable = false, length = 40)
    private String payer;

    @Column(name = "type", nullable = false, length = 10)
    private String typeReceipt;

}