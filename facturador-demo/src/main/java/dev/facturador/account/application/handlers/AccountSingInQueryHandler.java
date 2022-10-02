package dev.facturador.account.application.handlers;

import dev.facturador.account.domain.querys.AccountSingInQuery;
import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import dev.facturador.global.domain.abstractcomponents.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

/**
 * Manejador de la Query {@link AccountSingInQuery}
 */
@AllArgsConstructor
@Service
public class AccountSingInQueryHandler implements QueryHandler<HttpHeaders, AccountSingInQuery> {
    @Autowired
    private final ReactiveRequest<Class, String> reactiveRequest;

    /**
     * Solo sede la operacion al caso de uso
     */
    @Override
    public HttpHeaders handle(AccountSingInQuery query) throws Exception {
        var response = reactiveRequest.makeRequestNotHeader(
                "POST",
                "/login",
                BodyInserters.fromFormData(query.getKeys()),
                MediaType.APPLICATION_FORM_URLENCODED,
                String.class);
        //En que caso de que falle
        if (response == null) {
            throw new Exception("Response in login is null");
        }

        return response.getHeaders();
    }
}
