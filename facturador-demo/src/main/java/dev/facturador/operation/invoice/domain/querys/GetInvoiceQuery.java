package dev.facturador.operation.invoice.domain.querys;

import dev.facturador.global.domain.abstractcomponents.querys.Query;
import dev.facturador.operation.shared.domain.model.WholeOperationDisplayed;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetInvoiceQuery extends Query<WholeOperationDisplayed> {
    private final String invoiceNumber;
    private final String type;
    private final Long traderId;
    private final String repository;
}
