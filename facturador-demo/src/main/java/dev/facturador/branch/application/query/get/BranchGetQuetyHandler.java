package dev.facturador.branch.application.query.get;

import dev.facturador.branch.application.usecase.GetBranchUseCase;
import dev.facturador.branch.domain.Branch;
import dev.facturador.global.application.querys.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class BranchGetQuetyHandler implements QueryHandler<Branch, BranchGetQuery> {
    private final GetBranchUseCase useCase;

    public BranchGetQuetyHandler(GetBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Branch handle(BranchGetQuery query) throws Exception {
        return useCase.handleGetBranch(query.getBranchId());
    }
}
