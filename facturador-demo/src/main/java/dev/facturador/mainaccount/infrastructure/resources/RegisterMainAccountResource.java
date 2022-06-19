package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.mainaccount.application.command.register.MainAccountRegisterCommand;
import dev.facturador.mainaccount.application.query.singin.MainAccountSingInQuery;
import dev.facturador.mainaccount.domain.MainAccountRegister;
import dev.facturador.mainaccount.domain.MainAccountRegisteredResponse;
import dev.facturador.shared.application.commands.CommandBus;
import dev.facturador.shared.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth/mainaccounts")
public class RegisterMainAccountResource {
    private CommandBus commandBus;
    private QueryBus queryBus;

    public RegisterMainAccountResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    /**
     * Registra la cuenta principal en la base de datos
     * <br/>
     * Envia los datos necesarios para Iniciar sesion
     */
    @PostMapping
    public HttpEntity<MainAccountRegisteredResponse> singup(@Valid @RequestBody MainAccountRegister accountForRegister) throws Exception {

        MainAccountRegisterCommand command = MainAccountRegisterCommand.Builder.getInstance()
                .mainAccountRegister(accountForRegister).build();
        commandBus.handle(command);


        MainAccountSingInQuery query = MainAccountSingInQuery.Builder.getInstance()
                .keys(command.getMainAccountRegister().userRegister().username(),
                        command.getMainAccountRegister().userRegister().password()).build();
        var headers = queryBus.handle(query);

        var accesToken = "";
        var refreshToken = "";
        if (!Objects.requireNonNull(headers.get("accessToken")).isEmpty() &&
                !Objects.requireNonNull(headers.get("refreshToken")).isEmpty()) {
            accesToken = Objects.requireNonNull(headers.get("accessToken")).get(0);
            refreshToken = Objects.requireNonNull(headers.get("refreshToken")).get(0);
        }

        return ResponseEntity.created(new URI("http:localhost:8080/api/mainaccounts"))
                .body(new MainAccountRegisteredResponse(accesToken, refreshToken));
    }


}
