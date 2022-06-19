package dev.facturador.branchaccount.domain;

import dev.facturador.branch.domain.Branch;
import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "branch_account")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class BranchAccount implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Id
    @Column(name = "id_branch_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBranchAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_main_account_creator", nullable = false)
    private MainAccount accountBranchOwner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch_assigned", nullable = false)
    private Branch branchAssigned;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User userBranchAccount;

    @Column(name = "date_of_create", nullable = false, updatable = false)
    private LocalDateTime createAt;
}