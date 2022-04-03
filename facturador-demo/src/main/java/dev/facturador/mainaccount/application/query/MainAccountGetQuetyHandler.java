package dev.facturador.mainaccount.application.query;

import dev.facturador.mainaccount.application.usecases.GetTraderRelatedMainAccountUseCase;
import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.exception.MainAccountNotExists;
import dev.facturador.shared.application.querybus.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class MainAccountGetQuetyHandler implements QueryHandler<MainAccount, MainAccountGetQuery> {
    private GetTraderRelatedMainAccountUseCase useCase;

    public MainAccountGetQuetyHandler(GetTraderRelatedMainAccountUseCase useCase){
        this.useCase = useCase;
    }
    @Override
    public MainAccount handle(MainAccountGetQuery query) throws MainAccountNotExists {
        var user = useCase.handle(query.getMainAccountIdUsername().getUsername());
        if (user == null) {
            throw new MainAccountNotExists("No existe una cuenta con este username");
        }
        return user;
    }
}
