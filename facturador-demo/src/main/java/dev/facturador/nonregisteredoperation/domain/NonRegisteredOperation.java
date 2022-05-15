package dev.facturador.nonregisteredoperation.domain;

import dev.facturador.nonregisteredpartner.domain.NonRegisteredPartner;
import dev.facturador.operation.domain.Operation;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "non_registered_operation")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public final class NonRegisteredOperation implements Serializable {
    public static final Long serialVersinUID = 1L;

    @EmbeddedId
    private NonRegisteredOperationPK keyFromNonRegisteredOperation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_non_registered_partner_requested", referencedColumnName = "id_non_registered_partner", updatable = false, insertable = false)
    private NonRegisteredPartner nonRegisteredOperationPK;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_requester", referencedColumnName = "id_point_of_sale_issuing", updatable = false, insertable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", updatable = false, insertable = false)
    })
    private Operation operation;

}

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class NonRegisteredOperationPK implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Column(name = "id_non_registered_partner_requested", nullable = false)
    private long idNonRegisteredOperation;
    @Column(name = "id_point_of_sale_requester", nullable = false)
    private long idPointOfSaleIssuing;
    @Column(name = "date_of_issue", updatable = false, nullable = false)
    private LocalDateTime createAt;
}
