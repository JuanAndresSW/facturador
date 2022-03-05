package dev.facturador.registeredoperation.domain;

import dev.facturador.operation.domain.Operation;
import dev.facturador.partner.domain.Partner;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "operacion_real")
@Getter
@Setter
@NoArgsConstructor
public final class RegisteredOperation implements Serializable {
    public static final long serialVersinUID = 1L;
    @Id
    @Column(name = "id_op_real")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns(value = {
            @JoinColumn(name = "id_trader", referencedColumnName = "id_trader_requester",
                    updatable = false, insertable = false),
            @JoinColumn(name = "id_point_of_sale", referencedColumnName = "id_point_of_sale_requester",
                    updatable = false, insertable = false),
            @JoinColumn(name = "id_partner_trader", referencedColumnName = "id_trader_requested",
                    updatable = false, insertable = false),
            @JoinColumn(name = "id_partner", referencedColumnName = "id_point_of_sale_requested",
                    updatable = false, insertable = false)
    })
    private Partner operationReallySos;

    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing",
                    updatable = false, insertable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue",
                    updatable = false, insertable = false)
    })
    private Operation entryDocReally;


}

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
class RegisteredOperationPK implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Column(name = "id_trader")
    private long traderRequester;
    @Column(name = "id_point_of_sale")
    private long pointOfSaleRequester;
    @Column(name = "id_partner_trader")
    private long traderRequestedId;
    @Column(name = "id_partner")
    private long pointOfSaleRequested;
    @Column(name = "id_point_of_sale_issuing")
    private long docIssuingPointOfSale;
    @Column(name = "date_of_issue")
    private LocalDateTime createAt;
}
