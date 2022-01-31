package dev.facturador.entities;

import lombok.*;
import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "punto_venta")
@NoArgsConstructor @Getter @Setter @ToString
public final class PuntoVenta {

    @Id
    @Column(name = "id_punto_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPointOfSale;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_comerciante", nullable = false)
    private Comerciante pointOfSaleOwner;

    @Column(name = "nombre", nullable = false, length = 30)
    private String name;

    @Column(name = "direccion", nullable = false, length = 50)
    private String address;

    @Column(name = "email", nullable = false, length = 320)
    private String email;


    public PuntoVenta(Integer idPointOfSale) {
        this.idPointOfSale = idPointOfSale;
    }


}