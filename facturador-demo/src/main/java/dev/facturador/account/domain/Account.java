package dev.facturador.account.domain;


import dev.facturador.global.domain.sharedpayload.Vat;
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
    private LocalDateTime createAt;

    public Account(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public static Account create(String username) {
        var account = new Account();
        account.setOwnerTrader(null);
        account.setAccountId(null);
        account.setCreateAt(null);
        account.setOwnerUser(new User(username));
        return account;
    }
    /**Named Contructor para el registro de cuenta*/
    public static Account create(AccountRegister request) {
        var account = new Account();
        account.setOwnerTrader(new Trader(
                request.traderRegister().cuit(),
                request.traderRegister().businessName(),
                0, 0));

        String vatName = request.traderRegister().vatCategory();
        account.getOwnerTrader().setVat(defineVat(vatName));

        String passwordHash = hashPassword(request.userRegister().password());
        account.setOwnerUser(new User(
                request.userRegister().username(),
                passwordHash,
                request.userRegister().email()));

        if (StringUtils.hasText(request.userRegister().avatar())) {
            account.getOwnerUser().setUserAvatar(new UserAvatar(request.userRegister().avatar(), account.getOwnerUser()));
        }

        account.getOwnerTrader().setPointsOfSaleControl(new PointsOfSaleControl(0, 0, account.getOwnerTrader()));
        account.setCreateAt(LocalDateTime.now());
        return account;
    }
    /**Named Contructor para la actualizacion de la cuenta*/
    public static Account create(AccountUpdate request, Account account) {
        if (StringUtils.hasText(request.getUserUpdate().updatedUsername())) {
            account.getOwnerUser().setUsername(request.getUserUpdate().updatedUsername());
        }
        if (StringUtils.hasText(request.getUserUpdate().updatedPassword())) {
            String passwordHash = hashPassword(request.getUserUpdate().updatedPassword());
            account.getOwnerUser().setPassword(passwordHash);
        }
        if (StringUtils.hasText(request.getUserUpdate().updatedAvatar())) {
            account.getOwnerUser().getUserAvatar().setAvatar(request.getUserUpdate().updatedAvatar());
        } else {
            account.getOwnerUser().getUserAvatar().setAvatar("");
        }
        if (StringUtils.hasText(request.getTraderUpdate().updatedBusinessName())) {
            account.getOwnerTrader().setName(request.getTraderUpdate().updatedBusinessName());
        }
        if (StringUtils.hasText(request.getTraderUpdate().updatedCuit())) {
            account.getOwnerTrader().setCuit(request.getTraderUpdate().updatedCuit());
        }
        if (StringUtils.hasText(request.getTraderUpdate().updatedVatCategory())) {
            String vatName = request.getTraderUpdate().updatedVatCategory();
            account.getOwnerTrader().setVat(defineVat(vatName));
        }
        return account;
    }
    /**Hace el hash de la contraseña para antes de guardar*/
    public static String hashPassword(String password) {
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        return argon2.encode(password);
    }
    /**Pasa la categoria de String a ENUM*/
    public static Vat defineVat(String vatName) {
        if (vatName.contains("Inscripto")) {
            return Vat.REGISTERED_RESPONSIBLE;
        }
        return Vat.MONOTAX_RESPONSIBLE;
    }

    @Override
    public String toString() {
        return "Account{" +
                "mainAccountId=" + accountId +
                ", accountOwner=" + ownerTrader +
                ", userMainAccount=" + ownerUser +
                ", createDate=" + createAt +
                '}';
    }
}
