package dev.facturador.mainaccount.application.query.singin;

import dev.facturador.mainaccount.application.usecases.SingInUseCase;
import dev.facturador.shared.application.querys.QueryHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class MainAccountSingInQueryHandler
        implements QueryHandler<HttpHeaders, MainAccountSingInQuery> {

    private SingInUseCase useCase;

    public MainAccountSingInQueryHandler(SingInUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public HttpHeaders handle(MainAccountSingInQuery query) throws Exception {

        var headers = this.useCase.handle(query).getHeaders();

        return headers;
    }
}
