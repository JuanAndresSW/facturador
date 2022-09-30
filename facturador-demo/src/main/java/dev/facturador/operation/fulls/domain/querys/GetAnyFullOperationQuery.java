package dev.facturador.operation.fulls.domain.querys;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import dev.facturador.operation.fulls.domain.model.FullOperationDisplayed;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAnyFullOperationQuery extends Query<FullOperationDisplayed> {
    private final String operationNumber;
    private final String type;
    private final Long traderId;
    private final Long branchId;
    private final String repository;
}
