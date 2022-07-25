package dev.facturador.account.application.query.handlers;

import dev.facturador.account.application.query.AccountSingInQuery;
import dev.facturador.account.application.usecases.SignInAccountUseCase;
import dev.facturador.global.application.querys.QueryHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**Manejador de la Query {@link AccountSingInQuery}*/
@Component
public class AccountSingInQueryHandler
        implements QueryHandler<HttpHeaders, AccountSingInQuery> {

    private final SignInAccountUseCase useCase;

    public AccountSingInQueryHandler(SignInAccountUseCase useCase) {
        this.useCase = useCase;
    }

    /**Solo sede la operacion al caso de uso*/
    @Override
    public HttpHeaders handleGetBranch(AccountSingInQuery query) throws Exception {

        var headers = this.useCase.handleSignIn(query).getHeaders();

        return headers;
    }
}
