package dev.facturador.mainaccount.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.facturador.branchaccount.domain.BranchAccount;
import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;
import dev.facturador.shared.domain.Vat;
import dev.facturador.trader.domain.Trader;
import dev.facturador.user.domain.User;
import dev.facturador.user.domain.UserAvatar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collection;

@SuppressWarnings("ALL")
@Entity
@Table(name = "main_account")
@NoArgsConstructor
@Getter
@Setter
public final class MainAccount {
    @Id
    @Column(name = "id_main_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMainAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trader", nullable = false, unique = true)
    private Trader accountOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User userMainAccount;

    @Column(name = "date_of_create", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @JsonBackReference
    @OneToMany(mappedBy = "accountBranchOwner", cascade = CascadeType.ALL)
    private Collection<BranchAccount> mainAccountChilds;

    @Override
    public String toString() {
        return "MainAccount{" +
                "mainAccountId=" + idMainAccount +
                ", accountOwner=" + accountOwner +
                ", userMainAccount=" + userMainAccount +
                ", createDate=" + createAt +
                '}';
    }
}
