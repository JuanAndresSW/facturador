package dev.facturador.promissorynote.domain;

import dev.facturador.operation.domain.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "promissory_note")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class PromissoryNote {

    @Id
    @Column(name = "id_promissory_note")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPromissoryNote;

    @Column(name = "num_promissory_note", nullable = false)
    private int promissoryNoteNum;

    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing", nullable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Operation dataOperation;

    @Column(name = "amount", nullable = false, scale = 2)
    private double amount;

    @Column(name = "beneficiary", nullable = false, length = 255)
    private String beneficiary;

    @Column(name = "contact", nullable = false, length = 255)
    private String contact;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "protest", nullable = false)
    private boolean protest;

    @Column(name = "stamp", nullable = false)
    private boolean stamp;
}