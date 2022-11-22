package dev.facturador.operation.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
public class DocumentHistory implements Serializable {
    private Long operationId;
    private String documentClass;
    private String documentType;
    private String operationNumber;
    private String receiverName;
    private String receiverCuit;
    private String issueDate;
    private Long branchId;
}
