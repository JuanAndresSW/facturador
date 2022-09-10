package dev.facturador.operation.shared.domain.entity;

import dev.facturador.operation.shared.domain.AllVatCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad Enviadora
 */
@Entity
@Table(name = "receiver")
@NoArgsConstructor
@Getter
@Setter
public final class Receiver implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_receiver")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiverId;

    @Column(name = "receiver_code", nullable = false, length = 15)
    private String receiverCode;
    @Column(name = "receiver_name", nullable = false, length = 30)
    private String receiverName;
    @Column(name = "receiver_address", nullable = false, length = 45)
    private String receiverAddress;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "receiver_vat_category", nullable = false,
            columnDefinition = "enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE','EXCEMPT','END_CONSUMER','OTHER')")
    private AllVatCategory receiverVatCategory;

    @Column(name = "receiver_postal_code", nullable = false, length = 10)
    private String receiverPostalCode;
    @Column(name = "receiver_locality", nullable = false, length = 45)
    private String receiverLocality;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "id_operation_parent", nullable = false, updatable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operationReceiver;

    @Override
    public String toString() {
        return "Receiver{" +
                "receiverCode='" + receiverCode + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverVatCategory=" + receiverVatCategory +
                ", receiverPostalCode='" + receiverPostalCode + '\'' +
                ", receiverLocality='" + receiverLocality + '\'' +
                '}';
    }
}
