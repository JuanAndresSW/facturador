package dev.facturador.entities;

import dev.facturador.entities.enums.EstadoSolicitud;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "lista_socios")
@Getter
@Setter
@NoArgsConstructor
public final class ListaSocios implements Serializable {
    public static final long serialVersinUID = 1L;
    @EmbeddedId
    private ListaSociosPK listaSociosPK;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime issueDate;

    @Column(name = "num_usos", nullable = false)
    private int numUse;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "estado_solicitud", nullable = false,
            columnDefinition = "ENUM('E', 'A', 'B')")
    private EstadoSolicitud statusRequest;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comerciante_solicitante", referencedColumnName = "id_comerciante",
            updatable = false, insertable = false)
    private Comerciante traderRequester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta_solicitante", referencedColumnName = "id_punto_venta",
            updatable = false, insertable = false)
    private PuntoVenta pointOfSaleRequester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comerciante_solicitado", referencedColumnName = "id_comerciante",
            updatable = false, insertable = false)
    private Comerciante traderAdresser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_punto_venta_solicitado", referencedColumnName = "id_punto_venta",
            updatable = false, insertable = false)
    private PuntoVenta pointOfSaleAdresser;
}

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class ListaSociosPK implements Serializable {
    public static final long serialVersinUID = 1L;

    @Column(name = "id_comerciante_solicitante", nullable = false)
    private long traderRequesterId;
    @Column(name = "id_punto_venta_solicitante", nullable = false)
    private long pointOfSaleRequesterId;
    @Column(name = "id_comerciante_solicitado", nullable = false)
    private long traderAdreserId;
    @Column(name = "id_punto_venta_solicitado", nullable = false)
    private long pointOfSaleAdresserId;
}
