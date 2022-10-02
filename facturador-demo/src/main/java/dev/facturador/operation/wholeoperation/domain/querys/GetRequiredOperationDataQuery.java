package dev.facturador.operation.wholeoperation.domain.querys;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import dev.facturador.operation.wholeoperation.domain.model.DataReququiredOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRequiredOperationDataQuery extends Query<DataReququiredOperation> {
    private final Long traderId;
    private final Long pointOfSaleId;
    private final String header;
    private final String receiverCategory;
    private final String repository;
}
