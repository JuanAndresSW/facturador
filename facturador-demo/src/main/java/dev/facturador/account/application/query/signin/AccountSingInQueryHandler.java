package dev.facturador.account.application.query.signin;

import dev.facturador.account.application.usecases.SignInAccountUseCase;
import dev.facturador.global.application.querys.QueryHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AccountSingInQueryHandler
        implements QueryHandler<HttpHeaders, AccountSingInQuery> {

    private final SignInAccountUseCase useCase;

    public AccountSingInQueryHandler(SignInAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public HttpHeaders handle(AccountSingInQuery query) throws Exception {

        var headers = this.useCase.handleSignIn(query).getHeaders();

        return headers;
    }
}
