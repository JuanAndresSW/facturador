package dev.facturador.operation.fulls.domain.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FullOperationResponse {
    private String operationNumber;
    private String type;
}
