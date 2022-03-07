package dev.facturador.remittance.domain;

import dev.facturador.operation.domain.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "remittance")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Remittance {

    @Id
    @Column(name = "id_remittance", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRemito;

    @Column(name = "num_remittance", nullable = false)
    private int remitoNum;

    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing", nullable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Operation dataOperation;
}