package dev.facturador.account.application.usecases;

import dev.facturador.account.application.query.signin.AccountSingInQuery;
import dev.facturador.global.application.ReactiveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

@Service
public class SignInAccountUseCase {

    @Autowired
    private ReactiveRequest reactiveRequest;

    public ResponseEntity<String> handleSignIn(AccountSingInQuery query) throws Exception {
        var response = reactiveRequest.initRequest("/login",
                BodyInserters.fromFormData(query.getKeys()), MediaType.APPLICATION_FORM_URLENCODED);

        if (response == null) {
            throw new Exception("Response in login is null");
        }

        return response;
    }
}
