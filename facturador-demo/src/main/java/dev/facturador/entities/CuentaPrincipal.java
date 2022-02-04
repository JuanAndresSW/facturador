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
    private long idMainAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_comerciante", nullable = false, unique = true)
    private Comerciante accountOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detalles_cuenta", nullable = false, unique = true)
    private DetallesCuenta mainAccountDetails;

    @OneToMany(mappedBy = "mainAccount", cascade = CascadeType.REMOVE)
    private List<CuentaSecundaria> secondaryAccounts;

    private void addSecondaryAccount(CuentaSecundaria element){
        if(secondaryAccounts == null) secondaryAccounts = new LinkedList<>();
        this.secondaryAccounts.add(element);
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
