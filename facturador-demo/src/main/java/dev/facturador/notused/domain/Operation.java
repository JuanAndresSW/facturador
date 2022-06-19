package dev.facturador.notused.domain;

import dev.facturador.global.domain.sharedpayload.Flux;
import dev.facturador.pointofsale.domain.PointOfSale;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "operation")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class Operation implements Serializable {
    public static final Long serialVersinUID = 1L;

    @EmbeddedId
    private OperationPK operationPK;

    @Column(name = "flux", nullable = false,
            columnDefinition = "enum('I','O')")
    @Enumerated
    private Flux flux;

    @Column(name = "description", length = 60)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_point_of_sale_issuing", updatable = false, insertable = false,
            referencedColumnName = "id_point_of_sale")
    private PointOfSale pointofSale;
}

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
class OperationPK implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Column(name = "id_point_of_sale_issuing")
    private long idPointOfSaleIssuing;
    @Column(name = "date_of_issue")
    private LocalDateTime createAt;
}
