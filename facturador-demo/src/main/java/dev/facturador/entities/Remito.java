package dev.facturador.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "remito")
@NoArgsConstructor @Getter @Setter @ToString
public final class Remito {

    @Id
    @Column(name = "id_remito", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRemito;

    @Column(name = "num_remito", nullable = false)
    private int remitoNum;

    @Column(name = "id_punto_venta_emisor", nullable = false)
    private int issuingPointOfSale;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime remitoIssueDate;
}