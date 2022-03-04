package dev.facturador.check.domain;

import dev.facturador.docinicio.domain.DocInicio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "cheque")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Cheque {

    @Id
    @Column(name = "id_cheque")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCheck;

    @Column(name = "num_cheque", nullable = false)
    private int numCheck;

    @JoinColumns(value = {
            @JoinColumn(name = "id_punto_venta_emisor", referencedColumnName = "id_punto_venta_emisor", nullable = false),
            @JoinColumn(name = "fecha_emision", referencedColumnName = "fecha_creacion", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private DocInicio skeletonDocOfCheck;

    @Column(name = "serie", nullable = false, length = 1)
    private String serie;

    @Column(name = "cantidad", nullable = false, scale = 2)
    private double amountCheck;

    @Column(name = "banco", nullable = false, length = 20)
    private String bankName;

    @Column(name = "cruzado", nullable = false)
    private boolean crossed;


}