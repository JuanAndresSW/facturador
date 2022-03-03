package dev.facturador.actualoperation.domain;

import dev.facturador.docinicio.domain.DocInicio;
import dev.facturador.grouppartner.domain.ListaSocios;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "operacion_real")
@Getter
@Setter
@NoArgsConstructor
public final class OperacionReal implements Serializable {
    public static final long serialVersinUID = 1L;
    @Id
    @Column(name = "id_op_real")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns(value = {
            @JoinColumn(name = "id_comerciante_dueño_uno", referencedColumnName = "id_comerciante_solicitante",
                    updatable = false, insertable = false),
            @JoinColumn(name = "id_punto_venta_socio_uno", referencedColumnName = "id_punto_venta_solicitante",
                    updatable = false, insertable = false),
            @JoinColumn(name = "id_comerciante_dueño_dos", referencedColumnName = "id_comerciante_solicitado",
                    updatable = false, insertable = false),
            @JoinColumn(name = "id_punto_venta_socio_dos", referencedColumnName = "id_punto_venta_solicitado",
                    updatable = false, insertable = false)
    })
    private ListaSocios operationReallySos;

    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_punto_venta_emisor_doc", referencedColumnName = "id_punto_venta_emisor",
                    updatable = false, insertable = false),
            @JoinColumn(name = "fecha_creacion_doc", referencedColumnName = "fecha_creacion",
                    updatable = false, insertable = false)
    })
    private DocInicio entryDocReally;


}

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
class OperacionRealPK implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Column(name = "id_comerciante_dueño_uno")
    private long traderRequester;
    @Column(name = "id_punto_venta_socio_uno")
    private long pointOfSaleRequester;
    @Column(name = "id_comerciante_dueño_dos")
    private long traderRequestedId;
    @Column(name = "id_punto_venta_socio_dos")
    private long pointOfSaleRequested;
    @Column(name = "id_punto_venta_emisor_doc")
    private long docIssuingPointOfSale;
    @Column(name = "fecha_creacion_doc")
    private LocalDateTime createAt;
}
