package dev.facturador.operation.wholeoperation.domain.querys;

import dev.facturador.global.domain.abstractcomponents.querys.Query;
import dev.facturador.operation.wholeoperation.domain.model.WholeOperationDisplayed;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAnyWholeOperationQuery extends Query<WholeOperationDisplayed> {
    private final String operationNumber;
    private final String type;
    private final Long traderId;
    private final String repository;
}
