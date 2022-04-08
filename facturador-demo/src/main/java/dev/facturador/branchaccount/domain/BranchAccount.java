package dev.facturador.branchaccount.domain;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.branch.domain.Branch;
import dev.facturador.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "branch_account")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class BranchAccount {
    @Id
    @Column(name = "id_branch_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBranchAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_point_of_sale_asigned", nullable = false)
    private Branch branchAsigned;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User userBranchAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_main_account_creator", nullable = false)
    private MainAccount accountBranchOwner;

    @Column(name = "date_of_create", nullable = false, updatable = false)
    private LocalDateTime createAt;
}