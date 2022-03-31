package dev.facturador.operation.domain;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.shared.domain.shared.Flux;
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
public final class Operation implements Serializable {
    public static final Long serialVersinUID = 1L;

    @EmbeddedId
    private OperationPK operationPK;

    @Column(name = "flux", nullable = false,
            columnDefinition = "enum('I','O')")
    @Enumerated(value = EnumType.STRING)
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
