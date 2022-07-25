package dev.facturador.account.application.usecases;

import dev.facturador.account.application.query.AccountSingInQuery;
import dev.facturador.global.application.ReactiveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;

/**Se en carga de llamar a la autenticación*/
@Service
public class SignInAccountUseCase {
    //Clase para hacer Request con WebFlux
    @Autowired
    private ReactiveRequest reactiveRequest;

    public ResponseEntity<String> handleSignIn(AccountSingInQuery query) throws Exception {
        //Hace una requesta /login para hacer la autenticacion
        var response = reactiveRequest.initRequest("/login",
                BodyInserters.fromFormData(query.getKeys()), MediaType.APPLICATION_FORM_URLENCODED);
        //En que caso de que falle
        if (response == null) {
            throw new Exception("Response in login is null");
        }

        return response;
    }
}
