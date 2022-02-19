package dev.facturador.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("ALL")
@Entity
@Table(name="cuenta_principal")
@NoArgsConstructor @Getter @Setter
public final class CuentaPrincipal {

    @Id
    @Column(name="id_cuenta_principal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mainAccountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_comerciante", nullable = false, unique = true)
    private Comerciante accountOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuarios userMainAccount;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @JsonBackReference
    @OneToMany(mappedBy = "secondaryAccountOwner", cascade = CascadeType.ALL)
    private Collection<CuentaSecundaria> mainAccountChilds;

    private void addSecondaryAccount(CuentaSecundaria element){
        if(mainAccountChilds == null) mainAccountChilds = new LinkedList<>();
        this.mainAccountChilds.add(element);
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
