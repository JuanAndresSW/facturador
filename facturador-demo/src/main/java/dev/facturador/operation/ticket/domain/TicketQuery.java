package dev.facturador.operation.ticket.domain;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class TicketQuery extends Query<TicketResponse> {
    private Long operationId;
}
