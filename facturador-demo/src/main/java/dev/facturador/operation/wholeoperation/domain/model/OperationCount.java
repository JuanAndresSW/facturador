package dev.facturador.operation.wholeoperation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class OperationCount {
    private final Integer operationNumberCount;

}
