package dev.facturador.docinicio.domain;

import dev.facturador.pointofsale.domain.PuntoVenta;
import dev.facturador.shared.domain.Flujo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "doc_inicio")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class DocInicio implements Serializable {
    public static final Long serialVersinUID = 1L;

    @EmbeddedId
    private DocInicioPK docInicioPK;

    @Column(name = "flujo", nullable = false, columnDefinition = "ENUM('I', 'O')")
    @Enumerated(value = EnumType.STRING)
    private Flujo flux;

    @Column(name = "descipcion", length = 60)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_punto_venta_emisor", updatable = false, insertable = false, referencedColumnName = "id_punto_venta")
    private PuntoVenta pointofSale;
}

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
class DocInicioPK implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Column(name = "id_punto_venta_emisor")
    private long issuingPointOfSale;
    @Column(name = "fecha_creacion")
    private LocalDateTime dateOfIssueIni;
}
