package dev.facturador.account.domain;


import dev.facturador.global.domain.BeanClock;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
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

import static dev.facturador.global.domain.VatCategory.defineVat;

@Entity
@Table(name = "account")
@NoArgsConstructor
@Getter
@Setter
public final class Account {
    @Id
    @Column(name = "id_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trader", nullable = false, unique = true)
    private Trader ownerTrader;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User ownerUser;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Account(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public static Account create(String username) {
        var account = new Account();
        account.setOwnerTrader(null);
        account.setAccountId(null);
        account.setCreatedAt(null);
        account.setOwnerUser(new User(username));
        return account;
    }

    /**
     * Named Contructor para el registro de cuenta
     */
    public static Account create(AccountRegisterRestModel request, BeanClock clock) {
        var account = new Account();
        account.setOwnerTrader(new Trader(
                request.traderRegister().cuit(),
                request.traderRegister().businessName()));


        account.getOwnerTrader().setVatCategory(
                defineVat(request.traderRegister().vatCategory()));

        String passwordHash = hashPassword(request.userRegister().password());
        account.setOwnerUser(new User(
                request.userRegister().username(),
                passwordHash,
                request.userRegister().email()));

        if (StringUtils.hasText(request.userRegister().avatar())) {
            account.getOwnerUser().setUserAvatar(new UserAvatar(request.userRegister().avatar(), account.getOwnerUser()));
        }

        account.getOwnerTrader().setPointsOfSaleControl(new PointsOfSaleControl(0, 0, account.getOwnerTrader()));
        account.setCreatedAt(LocalDateTime.now(clock.clock()));
        return account;
    }

    /**
     * Named Contructor para la actualizacion de la cuenta
     */
    public static Account create(AccountUpdateRestModel request, Account account) {
        if (StringUtils.hasText(request.getUserUpdate().updatedUsername())) {
            account.getOwnerUser().setUsername(request.getUserUpdate().updatedUsername());
        }
        if (StringUtils.hasText(request.getUserUpdate().updatedPassword())) {
            String passwordHash = hashPassword(request.getUserUpdate().updatedPassword());
            account.getOwnerUser().setPassword(passwordHash);
        }
        if (StringUtils.hasText(request.getUserUpdate().updatedAvatar())) {
            account.getOwnerUser().getUserAvatar().setAvatar(request.getUserUpdate().updatedAvatar());
        }
        if (StringUtils.hasText(request.getTraderUpdate().updatedBusinessName())) {
            account.getOwnerTrader().setBusinessName(request.getTraderUpdate().updatedBusinessName());
        }
        if (StringUtils.hasText(request.getTraderUpdate().updatedCuit())) {
            account.getOwnerTrader().setCuit(request.getTraderUpdate().updatedCuit());
        }
        if (StringUtils.hasText(request.getTraderUpdate().updatedVatCategory())) {
            String vatName = request.getTraderUpdate().updatedVatCategory();
            account.getOwnerTrader().setVatCategory(defineVat(vatName));
        }
        return account;
    }

    /**
     * Hace el hash de la contraseña para antes de guardar
     */
    public static String hashPassword(String password) {
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        return argon2.encode(password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "mainAccountId=" + accountId +
                ", accountOwner=" + ownerTrader +
                ", userMainAccount=" + ownerUser +
                ", createDate=" + createdAt +
                '}';
    }
}
