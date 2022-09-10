package dev.facturador.operation.invoice.domain.querys;

import dev.facturador.global.domain.abstractcomponents.querys.Query;
import dev.facturador.operation.shared.domain.model.DataReququiredOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetInvoiceNumberQuery extends Query<DataReququiredOperation> {
    private final Long traderId;
    private final Long pointOfSaleId;
    private final String header;
    private final String receiverCategory;
}
