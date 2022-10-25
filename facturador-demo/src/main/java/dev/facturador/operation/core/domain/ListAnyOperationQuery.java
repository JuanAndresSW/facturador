package dev.facturador.operation.core.domain;

import dev.facturador.global.domain.abstractcomponents.query.Query;
import dev.facturador.operation.core.domain.entity.Operation;
import lombok.*;

import java.util.List;

@Builder
@Data
public class ListAnyOperationQuery extends Query<List<DocumentHistory>> {
    private String repository;
    private Long traderID;
    private Long branchId;
    private Long pointOfSaleNumber;
}
