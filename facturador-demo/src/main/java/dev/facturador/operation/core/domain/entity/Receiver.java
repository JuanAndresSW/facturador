package dev.facturador.operation.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.core.domain.AllVatCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @JsonIgnore
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
    private String receiverCity;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_operation_parent", nullable = false, updatable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operationReceiver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Receiver receiver = (Receiver) o;

        return new EqualsBuilder().append(getReceiverCode(), receiver.getReceiverCode()).append(getReceiverName(), receiver.getReceiverName()).append(getReceiverAddress(), receiver.getReceiverAddress()).append(getReceiverVatCategory(), receiver.getReceiverVatCategory()).append(getReceiverPostalCode(), receiver.getReceiverPostalCode()).append(getReceiverCity(), receiver.getReceiverCity()).isEquals();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getReceiverCode()).append(getReceiverName()).append(getReceiverAddress()).append(getReceiverVatCategory()).append(getReceiverPostalCode()).append(getReceiverCity()).toHashCode();
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "receiverCode='" + receiverCode + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverVatCategory=" + receiverVatCategory +
                ", receiverPostalCode='" + receiverPostalCode + '\'' +
                ", receiverLocality='" + receiverCity + '\'' +
                '}';
    }
}
