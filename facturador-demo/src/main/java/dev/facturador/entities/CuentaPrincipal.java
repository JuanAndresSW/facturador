package dev.facturador.entities;

import lombok.*;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name="cuenta")
@NoArgsConstructor @Getter @Setter
public final class CuentaPrincipal {

    @Id
    @Column(name="id_cuenta_principal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMainAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_comerciante", nullable = false, updatable = false, unique = true)
    private Comerciante accountOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detalles_cuenta", nullable = false, updatable = false, unique = true)
    private DetallesCuenta mainAccountDetails;

    @OneToMany(mappedBy = "mainAccount", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) private List<CuentaSecundaria> ownerSecondaryAccounts;

    private void addSecondaryAccount(CuentaSecundaria element){
        if(ownerSecondaryAccounts == null) ownerSecondaryAccounts = new LinkedList<>();
        this.ownerSecondaryAccounts.add(element);
    }

    @Override
    public String toString() {
        return "CuentaPrincipal{" +
                "idMainAccount=" + idMainAccount +
                ", accountOwner=" + accountOwner +
                ", mainAccountDetails=" + mainAccountDetails +
                '}';
    }
}
