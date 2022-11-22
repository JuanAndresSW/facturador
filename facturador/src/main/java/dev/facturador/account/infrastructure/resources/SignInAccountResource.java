package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.domain.AccountSignInRestModel;
import dev.facturador.account.domain.querys.AccountSignInQuery;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.LinkedHashMap;

import static org.springframework.http.ResponseEntity.ok;


/**
 * EndPoint para iniciar session
 */
@RestController
@RequestMapping(path = "/api/auth/accounts")
public class SignInAccountResource {
    private final PortQueryBus portQueryBus;

    @Autowired
    public SignInAccountResource(PortQueryBus portQueryBus) {
        this.portQueryBus = portQueryBus;
    }

    /**
     * Permite realizar un inicio de sesion en caso que la haya cerrado o expirado
     *
     * @param accountRestModel Es {@link AccountSignInRestModel}, contiene las credenciales de inicio de session
     * @return Datos necesarios para realizar operaciones una vez iniciada la sesion
     */
    @PostMapping("/log-in")
    public Mono<HttpEntity<LinkedHashMap<String, String>>> loginWithJSON(@Valid @RequestBody AccountSignInRestModel accountRestModel) throws Exception {
        var query = AccountSignInQuery.Builder.getInstance()
                .keys(accountRestModel.usernameOrEmail(), accountRestModel.password()).build();
        var response = portQueryBus.handle(query);


        return Mono.just(response).map(x -> ok().body(x));
    }


}
