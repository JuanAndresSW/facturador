package dev.facturador.operation.remittance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.operation.core.domain.entity.Operation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "remittance")
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class Remittance implements Serializable {
    public static final Long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @Column(name = "id_remittance")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long remittanceId;

    @Column(name = "observation", nullable = false, length = 65)
    private String observations;

    @JsonIgnore
    @Column(name = "count_remittance_number", nullable = false, length = 8)
    private Integer operationNumberCount;

    @Column(name = "remittance_number", nullable = false, length = 13)
    private String remittanceNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_operation_parent", nullable = false, referencedColumnName = "id_operation", unique = true)
    private Operation operation;

    @Override
    public String toString() {
        return "Remittance{" +
                "remittanceId=" + remittanceId +
                ", observations='" + observations + '\'' +
                ", operationNumberCount=" + operationNumberCount +
                ", remittanceNumber='" + remittanceNumber + '\'' +
                '}';
    }
}
