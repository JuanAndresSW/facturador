package dev.facturador.notused.domain;

import dev.facturador.branch.domain.Branch;
import dev.facturador.shared.domain.sharedpayload.RequestState;
import dev.facturador.trader.domain.Trader;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "partner")
@Getter
@Setter
@NoArgsConstructor
public final class Partner implements Serializable {
    public static final Long serialVersinUID = 1L;
    @EmbeddedId
    private PartnerPK partnerPK;

    @Column(name = "use_count", nullable = false)
    private int useCount;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime issueAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "request_state", nullable = false,
            columnDefinition = "enum('W','A','L')")
    private RequestState requestState;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trader_requester", referencedColumnName = "id_trader",
            updatable = false, insertable = false)
    private Trader traderRequester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch_requester", referencedColumnName = "id_branch",
            updatable = false, insertable = false)
    private Branch branchRequester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trader_requested", referencedColumnName = "id_trader",
            updatable = false, insertable = false)
    private Trader traderRequested;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch_requested", referencedColumnName = "id_branch",
            updatable = false, insertable = false)
    private Branch branchRequested;
}

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class PartnerPK implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Column(name = "id_trader_requester", nullable = false)
    private long idTraderRequester;
    @Column(name = "id_branch_requester", nullable = false)
    private long idBranchRequester;
    @Column(name = "id_trader_requested", nullable = false)
    private long idTraderRequested;
    @Column(name = "id_branch_requested", nullable = false)
    private long idBranchRequested;
}
