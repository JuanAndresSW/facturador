package dev.facturador.trader.application.query;

import dev.facturador.mainaccount.application.usecases.GetMainAccountUseCase;
import dev.facturador.mainaccount.domain.exception.MainAccountNotExists;
import dev.facturador.shared.application.querybus.QueryHandler;
import dev.facturador.trader.application.usecase.GetBranchesUseCase;
import dev.facturador.trader.domain.Trader;
import dev.facturador.trader.domain.TraderBranchCollection;
import org.springframework.stereotype.Component;

@Component
public class TraderGetBranchesQueryHandler implements QueryHandler<TraderBranchCollection, TraderGetBranchesQuery> {
    private GetBranchesUseCase useCase;

    public TraderGetBranchesQueryHandler(GetBranchesUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public TraderBranchCollection handle(TraderGetBranchesQuery query) throws MainAccountNotExists {
        var listBranch = useCase.handle(query.getTraderID());
        if(listBranch.getBranchInfoList().isEmpty()){
            throw
        }
        return listBranch;
    }

}
