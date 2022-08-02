package dev.facturador.branch.application.query.handlers;

import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.branch.application.usecase.BranchesSummaryUseCase;
import dev.facturador.branch.application.query.BranchesSummaryQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;

@AllArgsConstructor
@Component
public class BranchesSummaryQueryHandler implements QueryHandler<LinkedList<HashMap<String, Object>>, BranchesSummaryQuery> {
    private final BranchesSummaryUseCase useCase;

    @Override
    public LinkedList<HashMap<String, Object>> handleGetBranch(BranchesSummaryQuery query) throws Exception {
        if(!this.useCase.handleExists(query.getTraderId())){
            throw new Exception("No tienes sucursales");
        }
        return this.useCase.handleGetBranchesSummary(query.getTraderId());
    }
}
