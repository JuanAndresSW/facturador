package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.domain.AccountRegisterRestModel;
import dev.facturador.account.domain.commands.AccountRegisterCommand;
import dev.facturador.account.domain.querys.AccountSingInQuery;
import dev.facturador.global.domain.abstractcomponents.commands.CommandBus;
import dev.facturador.global.domain.abstractcomponents.querys.QueryBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * EndPoint para Registrar cuenta de usuario
 */
@RestController
@RequestMapping(path = "/api/auth/accounts")
public class RegisterAccountResource {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public RegisterAccountResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
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
    public ResponseEntity<Map<String, String>> signUp(@Valid @RequestBody AccountRegisterRestModel accountRestModel) throws Exception {

        var command = AccountRegisterCommand.builder()
                .accountRegisterRestModel(accountRestModel).build();

        commandBus.handle(command);

        var username = command.getAccountRegisterRestModel().userRegister().username();
        var passwordNoHash = command.getAccountRegisterRestModel().userRegister().password();

        var query = AccountSingInQuery.Builder.getInstance()
                .keys(username, passwordNoHash).build();

        var headers = queryBus.handle(query);

        var accesToken = Objects.requireNonNull(headers.get("accessToken")).get(0);
        var refreshToken = Objects.requireNonNull(headers.get("refreshToken")).get(0);

        return ResponseEntity.created(new URI("http:localhost:8080/api/mainaccounts"))
                .body(new HashMap<String, String>(
                        Map.of("accessToken", accesToken,
                                "refreshToken", refreshToken)));
    }

}
