package dev.facturador.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Table(name="cuenta_principal")
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
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuarios userMainAccount;

    @JsonBackReference
    @OneToMany(mappedBy = "mainAccount", cascade = CascadeType.ALL)
    private List<CuentaSecundaria> secondaryAccounts;

    private void addSecondaryAccount(CuentaSecundaria element){
        if(secondaryAccounts == null) secondaryAccounts = new LinkedList<>();
        this.secondaryAccounts.add(element);
    }

    //El ToString lo genero sin lombok porque el lombok tomaria el atributo de la relacion bi-direccional
    //Generando un bucle de toString que da una excepcion
    @Override
    public String toString() {
        return "CuentaPrincipal{" +
                "idMainAccount=" + idMainAccount +
                ", accountOwner=" + accountOwner +
                ", mainAccountDetails=" + userMainAccount +
                '}';
    }
}
