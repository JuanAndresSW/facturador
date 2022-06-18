package dev.facturador.account.infrastructure.resources;

import dev.facturador.account.application.command.register.AccountRegisterCommand;
import dev.facturador.account.application.query.signin.AccountSingInQuery;
import dev.facturador.account.domain.AccountRegister;
import dev.facturador.global.application.commands.CommandBus;
import dev.facturador.global.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Stream;

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
     * Registra la cuenta principal en la base de datos
     * <br/>
     * Envia los datos necesarios para Iniciar sesion
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> signUp(@Valid @RequestBody AccountRegister accountForRegister) throws Exception {

        AccountRegisterCommand command = AccountRegisterCommand.Builder.getInstance()
                .mainAccountRegister(accountForRegister).build();
        commandBus.handle(command);


        AccountSingInQuery query = AccountSingInQuery.Builder.getInstance()
                .keys(command.getAccountRegister().userRegister().username(),
                        command.getAccountRegister().userRegister().password()).build();
        var headers = queryBus.handle(query);

        var accesToken = "";
        var refreshToken = "";
        if (!Objects.requireNonNull(headers.get("accessToken")).isEmpty() &&
                !Objects.requireNonNull(headers.get("refreshToken")).isEmpty()) {
            accesToken = Objects.requireNonNull(headers.get("accessToken")).get(0);
            refreshToken = Objects.requireNonNull(headers.get("refreshToken")).get(0);
        }
        ;
        return ResponseEntity.created(new URI("http:localhost:8080/api/mainaccounts"))
                .body(new HashMap<String, String>(
                        Map.of("accessToken", accesToken, "refreshToken", refreshToken)));
    }


}
