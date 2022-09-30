package dev.facturador.operation.fulls.domain.querys;

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
    private final String receiverCategory;
    private final String repository;
}
