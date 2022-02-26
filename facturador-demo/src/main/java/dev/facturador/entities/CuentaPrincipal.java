package dev.facturador.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.facturador.bo.RegisterBo;
import dev.facturador.entities.enums.Vat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("ALL")
@Entity
@Table(name = "cuenta_principal")
@NoArgsConstructor
@Getter
@Setter
public final class CuentaPrincipal {
    @Id
    @Column(name = "id_cuenta_principal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mainAccountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comerciante", nullable = false, unique = true)
    private Comerciante accountOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuarios userMainAccount;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @JsonBackReference
    @OneToMany(mappedBy = "secondaryAccountOwner", cascade = CascadeType.ALL)
    private Collection<CuentaSecundaria> mainAccountChilds;

    public static CuentaPrincipal createMainAccountForRegister(RegisterBo tryRegister) {
        var account = new CuentaPrincipal();
        account.setCreateDate(LocalDateTime.now(ZoneId.systemDefault()));
        account.setAccountOwner(new Comerciante(tryRegister.getTraderBo().code(), tryRegister.getTraderBo().grossIncome(), tryRegister.getTraderBo().businessName(), 0, 0));

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
        account.setUserMainAccount(new Usuarios
                (tryRegister.getUserBo().username(), passwordHashed, tryRegister.getUserBo().email()));

        if (StringUtils.hasText(tryRegister.getUserBo().avatar())) {
            account.getUserMainAccount().setAvatarUser(new AvatarUsuario(tryRegister.getUserBo().avatar(), account.getUserMainAccount()));
        }

        return account;
    }

    /**
     * Se encarga del hash de la contrseña
     *
     * @param account RegisterBo contiene la contraseña
     * @return String
     */
    private static String hashPassword(RegisterBo account) {
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        String password = account.getUserBo().password();
        return argon2.encode(password);
    }

    @Override
    public String toString() {
        return "CuentaPrincipal{" +
                "mainAccountId=" + mainAccountId +
                ", accountOwner=" + accountOwner +
                ", userMainAccount=" + userMainAccount +
                ", createDate=" + createDate +
                '}';
    }
}
