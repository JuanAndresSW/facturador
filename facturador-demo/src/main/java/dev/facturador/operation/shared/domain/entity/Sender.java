package dev.facturador.operation.shared.domain.entity;

import dev.facturador.global.domain.VatCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad Recividora
 */
@Entity
@Table(name = "sender")
@NoArgsConstructor
@Getter
@Setter
public final class Sender implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_sender")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long senderId;

    @Column(name = "sender_code", nullable = false, length = 15)
    private String senderCode;
    @Column(name = "sender_name", nullable = false, length = 30)
    private String senderName;
    @Column(name = "sender_address", nullable = false, length = 45)
    private String senderAddress;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sender_vat_category", nullable = false,
            columnDefinition = "enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE')")
    private VatCategory senderVatCategory;

    @Column(name = "sender_contact", nullable = false, length = 128)
    private String senderContact;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "id_operation_parent", nullable = false, updatable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operationSender;

    @Override
    public String toString() {
        return "Sender{" +
                "senderId=" + senderId +
                ", senderCode='" + senderCode + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                ", senderVatCategory=" + senderVatCategory +
                ", senderContact='" + senderContact + '\'' +
                '}';
    }
}
