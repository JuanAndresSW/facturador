package dev.facturador.branch.application.query.handlers;

import dev.facturador.branch.application.query.BranchGetQuery;
import dev.facturador.branch.application.usecase.GetBranchUseCase;
import dev.facturador.branch.domain.Branch;
import dev.facturador.global.application.querys.QueryHandler;
import org.springframework.stereotype.Component;

/**Manejador de la Query {@link BranchGetQuery} devuelve la entidad {@link Branch}*/
@Component
public class BranchGetQueryHandler implements QueryHandler<Branch, BranchGetQuery> {
    private final GetBranchUseCase useCase;

    public BranchGetQueryHandler(GetBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Branch handleGetBranch(BranchGetQuery query) throws Exception {
        return useCase.handleGetBranch(query.getBranchId());
    }
}
