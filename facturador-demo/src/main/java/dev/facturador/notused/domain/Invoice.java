package dev.facturador.notused.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("ALL")
@Entity
@Table(name = "invoice")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Invoice implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Id
    @Column(name = "id_invoice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;

    @Column(name = "num_invoice", nullable = false)
    private int invoiceNum;

    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing", nullable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Operation dataOperation;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false,
            columnDefinition = "enum('A','B','C')")
    private TypeInvoice invoiceType;

    @Column(name = "tax", nullable = false, length = 2)
    private String tax;

    @Column(name = "vat", nullable = false, length = 1)
    private String vatCategory;

    @Column(name = "payment_form", length = 255, nullable = false)
    private String paymenForm;

}