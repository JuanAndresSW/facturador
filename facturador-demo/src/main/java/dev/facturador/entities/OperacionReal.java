package dev.facturador.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
            @JoinColumn(name = "id_comerciante_dueño_uno", referencedColumnName = "id_comerciante_solicitante", nullable = false),
            @JoinColumn(name = "id_punto_venta_socio_uno", referencedColumnName = "id_punto_venta_solicitante", nullable = false),
            @JoinColumn(name = "id_comerciante_dueño_dos", referencedColumnName = "id_comerciante_solicitado", nullable = false),
            @JoinColumn(name = "id_punto_venta_socio_dos", referencedColumnName = "id_punto_venta_solicitado", nullable = false)
    })
    private ListaSocios operationReallySos;
/*
    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_emisor_doc_real", referencedColumnName = "id_punto_venta_emisor"),
            @JoinColumn(name = "fecha_creacion_doc_inv", referencedColumnName = "fecha_creacion")
    })
    private DocInicio entryDocReally;
 */

    @Column(name = "fecha_creacion_doc", nullable = false, updatable = false)
    private LocalDateTime creationDate;
    @Column(name = "id_emisor_doc_real", nullable = false)
    private long realDocIssuer;
    @Column(name = "num_doc_real", nullable = false)
    private int docNum;
}
/*
@Embeddable
@NoArgsConstructor @Getter @Setter @ToString
class OperacionRealPK implements Serializable {
    public static final long serialVersinUID = 1L;

    @Column(name = "id_comerciante_solicitante")
    private long traderRequesterId;
    @Column(name = "id_punto_venta_solicitante")
    private long pointOfSaleRequesterId;
    @Column(name = "id_comerciante_solicitado")
    private long traderRequestedId;
    @Column(name = "id_punto_venta_solicitado")
    private long pointOfSaleRequested;
}
*/