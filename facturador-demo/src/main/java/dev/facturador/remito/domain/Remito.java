package dev.facturador.remito.domain;

import dev.facturador.docinicio.domain.DocInicio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "remito")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Remito {

    @Id
    @Column(name = "id_remito", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRemito;

    @Column(name = "num_remito", nullable = false)
    private int remitoNum;

    @JoinColumns(value = {
            @JoinColumn(name = "id_punto_venta_emisor", referencedColumnName = "id_punto_venta_emisor", nullable = false),
            @JoinColumn(name = "fecha_emision", referencedColumnName = "fecha_creacion", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private DocInicio skeletonDocOfRemito;
}