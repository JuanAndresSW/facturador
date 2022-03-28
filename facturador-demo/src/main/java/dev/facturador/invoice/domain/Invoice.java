package dev.facturador.invoice.domain;

import dev.facturador.operation.domain.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "invoice")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Invoice {

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

    @Column(name = "vatCategory", nullable = false, length = 1)
    private String vatDetail;

    @Column(name = "paymen_form", length = 255, nullable = false)
    private String paymenForm;

}