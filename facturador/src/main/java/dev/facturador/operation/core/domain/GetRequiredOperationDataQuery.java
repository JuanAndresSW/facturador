package dev.facturador.operation.core.domain;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRequiredOperationDataQuery extends Query<DataRequiredOperation> {
    private final Long traderId;
    private final Long pointOfSaleId;
    private final String header;
    private final String repository;
    private final String category;
}
