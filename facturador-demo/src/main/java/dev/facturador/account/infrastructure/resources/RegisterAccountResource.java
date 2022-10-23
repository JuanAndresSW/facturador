package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.domain.AccountRegisterRestModel;
import dev.facturador.account.domain.commands.AccountRegisterCommand;
import dev.facturador.account.domain.querys.AccountSignInQuery;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandBus;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

import static org.springframework.http.ResponseEntity.created;

/**
 * EndPoint para Registrar cuenta de usuario
 */
@RestController
@RequestMapping(path = "/api/auth/accounts")
public class RegisterAccountResource {
    private final PortCommandBus portCommandBus;
    private final PortQueryBus portQueryBus;

    public RegisterAccountResource(PortCommandBus portCommandBus, PortQueryBus portQueryBus) {
        this.portCommandBus = portCommandBus;
        this.portQueryBus = portQueryBus;
    }

    /**
     * En este endPoint se realiza el registro para tener una cuenta
     * que tenga acceso al Sistema (Basado en Tokens)
     *
     * @param accountRestModel Objeto con los datos necesario para ingresar al sistema
     * @return Devuelve el Token de acceso y el token de refrescamiento
     * @throws Exception
     */
    @PostMapping
    public Mono<HttpEntity<Map<String, String>>> signUp(@Valid @RequestBody AccountRegisterRestModel accountRestModel) throws Exception {

        var command = AccountRegisterCommand.builder()
                .accountRegisterRestModel(accountRestModel).build();

        portCommandBus.handle(command);

        var username = command.getAccountRegisterRestModel().userRegister().username();
        var passwordNoHash = command.getAccountRegisterRestModel().userRegister().password();

        var query = AccountSignInQuery.Builder.getInstance()
                .keys(username, passwordNoHash)
                .build();

        var response = portQueryBus.handle(query);

        response.remove("username");
        response.remove("IDTrader");


        return Mono.just(response).map(x -> created(URI.create("http:localhost:8080/api/accounts")).body(x));
    }

}
