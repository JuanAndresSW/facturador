package dev.facturador.mainaccount.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.facturador.branchaccount.domain.BranchAccount;
import dev.facturador.shared.domain.sharedpayload.Vat;
import dev.facturador.trader.domain.Trader;
import dev.facturador.user.domain.User;
import dev.facturador.user.domain.subdomain.UserAvatar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@Table(name = "main_account")
@NoArgsConstructor
@Getter
@Setter
public final class MainAccount {
    @Id
    @Column(name = "id_main_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMainAccount;

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

    public static MainAccount create(String username) {
        var account = new MainAccount();
        account.setAccountOwner(null);
        account.setMainAccountChilds(null);
        account.setIdMainAccount(null);
        account.setCreateAt(null);
        account.setUserMainAccount(new User(username));
        return account;
    }
    public static MainAccount create(MainAccountRegister request) {
        var account = new MainAccount();
        account.setAccountOwner(new Trader(
                request.traderRegister().code(),
                request.traderRegister().businessName(),
                0, 0));

        String vatName = request.traderRegister().vatCategory();
        account.getAccountOwner().setVat(defineVat(vatName));

        String passwordHash = hashPassword(request.userRegister().password());
        account.setUserMainAccount(new User(
                request.userRegister().username(),
                passwordHash,
                request.userRegister().email()));

        if (StringUtils.hasText(request.userRegister().avatar())) {
            account.getUserMainAccount().setAvatarUser(new UserAvatar(request.userRegister().avatar(), account.getUserMainAccount()));
        }
        account.setCreateAt(LocalDateTime.now());
        return account;
    }

    public static MainAccount create(MainAccountUpdate request, MainAccount account) {
        if (StringUtils.hasText(request.getUserUpdate().newUsername())) {
            account.getUserMainAccount().setUsername(request.getUserUpdate().newUsername());
        }
        if (StringUtils.hasText(request.getUserUpdate().newPassword())) {
            String passwordHash = hashPassword(request.getUserUpdate().newPassword());
            account.getUserMainAccount().setPassword(passwordHash);
        }
        if (StringUtils.hasText(request.getUserUpdate().newAvatar())) {
            account.getUserMainAccount().getAvatarUser().setAvatar(request.getUserUpdate().newAvatar());
        }
        if (StringUtils.hasText(request.getTraderUpdate().newBusinessName())) {
            account.getAccountOwner().setName(request.getTraderUpdate().newBusinessName());
        }
        if (StringUtils.hasText(request.getTraderUpdate().newCode())) {
            account.getAccountOwner().setUniqueKey(request.getTraderUpdate().newCode());
        }
        if (StringUtils.hasText(request.getTraderUpdate().newVatCategory())) {
            String vatName = request.getTraderUpdate().newVatCategory();
            account.getAccountOwner().setVat(defineVat(vatName));
        }
        return account;
    }

    public static String hashPassword(String password) {
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        return argon2.encode(password);
    }

    public static Vat defineVat(String vatName) {
        if (vatName.contains("Inscripto")) {
            return Vat.REGISTERED_RESPONSIBLE;
        }
        return Vat.MONOTAX_RESPONSIBLE;
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
