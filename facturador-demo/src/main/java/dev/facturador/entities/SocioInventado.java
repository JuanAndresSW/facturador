package dev.facturador.entities;

import dev.facturador.entities.enums.Vat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "socio_inventado")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class SocioInventado {

    @Id
    @Column(name = "id_socio_inventado", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partnerUnTrueId;

    @Column(name = "clave_unica", length = 15, nullable = false)
    private String uniqueKey;

    @Column(name = "nombre_punto_venta", length = 20, nullable = false)
    private String namePointOfSale;

    @Column(name = "direccion", nullable = false, length = 50)
    private String address;

    @Column(name = "localidad", length = 20, nullable = false)
    private String lacation;

    @Column(name = "cp", length = 10, nullable = false)
    private String cp;

    @Column(name = "iva", nullable = false,
            columnDefinition = "ENUM('Responsable Inscripto', 'Monotributista', 'Sujeto Exento')")
    @Enumerated(value = EnumType.STRING)
    private Vat vat;
    @Column(name = "numero_usos", nullable = false)
    private int numUse;

}