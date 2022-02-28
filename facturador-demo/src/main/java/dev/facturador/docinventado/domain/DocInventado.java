package dev.facturador.docinventado.domain;

import dev.facturador.docinicio.domain.DocInicio;
import dev.facturador.partneruntrue.domain.SocioInventado;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "doc_inventado")
@Getter
@Setter
@NoArgsConstructor
public final class DocInventado implements Serializable {
    public static final long serialVersinUID = 1L;

    @EmbeddedId
    private DocInventadoPK docUnTruePK;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(value = {
            @JoinColumn(name = "punto_venta_emisor_doc_inv", referencedColumnName = "id_punto_venta_emisor", updatable = false, insertable = false),
            @JoinColumn(name = "fecha_creacion_doc_inv", referencedColumnName = "fecha_creacion", updatable = false, insertable = false)
    })
    private DocInicio entryDoc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_socio_inv_receptor", referencedColumnName = "id_socio_inventado", updatable = false, insertable = false)
    private SocioInventado partnerUnTrue;
}

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
class DocInventadoPK implements Serializable {
    public static final long serialVersinUID = 1L;

    @Column(name = "punto_venta_emisor_doc_inv", nullable = false)
    private long issuingPointOfSale;
    @Column(name = "id_socio_inventado", nullable = false)
    private long receiptPartnerUnTrue;
    @Column(name = "fecha_creacion_doc_inv", updatable = false, nullable = false)
    private LocalDateTime dateOfIssueInv;
}
