package dev.facturador.mainaccount.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.facturador.branchaccount.domain.BranchAccount;
import dev.facturador.mainaccount.domain.bo.RegisterRequest;
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

    public static MainAccount createMainAccountForRegister(RegisterRequest tryRegister) {
        var account = new MainAccount();
        account.setCreateAt(LocalDateTime.now(Clock.systemDefaultZone()));
        account.setAccountOwner(new Trader(tryRegister.getTraderBo().code(), tryRegister.getTraderBo().grossIncome(), tryRegister.getTraderBo().businessName(), 0, 0));

        String vatName = tryRegister.getTraderBo().vatCategory();
        if (vatName.contains("Responsable")) {
            account.getAccountOwner().setVat(Vat.RESPONSABLE_INSCRIPTO);
        }
        if (vatName.contains("Mono")) {
            account.getAccountOwner().setVat(Vat.MONOTRIBUTISTA);
        }
        if (vatName.contains("Sujeto")) {
            account.getAccountOwner().setVat(Vat.SUJETO_EXENTO);
        }
        String passwordHashed = hashPassword(tryRegister);
        account.setUserMainAccount(new User
                (tryRegister.getUserBo().username(), passwordHashed, tryRegister.getUserBo().email()));

        if (StringUtils.hasText(tryRegister.getUserBo().avatar())) {
            account.getUserMainAccount().setAvatarUser(new UserAvatar(tryRegister.getUserBo().avatar(), account.getUserMainAccount()));
        }

        return account;
    }

    /**
     * Se encarga del hash de la contrseña
     *
     * @param account RegisterRequest contiene la contraseña
     * @return String
     */
    private static String hashPassword(RegisterRequest account) {
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        String password = account.getUserBo().password();
        return argon2.encode(password);
    }


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
