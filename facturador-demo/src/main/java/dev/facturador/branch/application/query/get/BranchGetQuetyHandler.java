package dev.facturador.branch.application.query.get;

import dev.facturador.branch.application.usecase.GetBranchUseCase;
import dev.facturador.branch.domain.Branch;
import dev.facturador.shared.application.querys.QueryHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BranchGetQuetyHandler implements QueryHandler<Branch, BranchGetQuery> {
    private GetBranchUseCase useCase;

    public BranchGetQuetyHandler(GetBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Branch handle(BranchGetQuery query) throws Exception {
        return useCase.get(query.getBranchID());
    }
}
