package dev.facturador.mainaccount.infrastructure;

import dev.facturador.mainaccount.application.command.register.MainAccountRegisterCommand;
import dev.facturador.mainaccount.domain.MainAccountRegister;
import dev.facturador.mainaccount.domain.MainAccountRegisteredResponse;
import dev.facturador.shared.application.comandbus.CommandBus;
import dev.facturador.trader.domain.TraderRegister;
import dev.facturador.user.domain.UserRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class RegisterMainAccountResource {
    private CommandBus commandBus;

    public RegisterMainAccountResource(CommandBus commandBus){
        this.commandBus = commandBus;
    }
    /**
     * Registra la cuenta principal en la base de datos
     * <br/>
     * Envia los datos necesarios para Iniciar sesion
     */
    @PostMapping("/mainaccounts")
    public HttpEntity<?> singup
    (@Valid @RequestParam("user") UserRegister user, @Valid @RequestParam("trader") TraderRegister trader) throws Exception {

        MainAccountRegisterCommand command = MainAccountRegisterCommand.Builder.getInstance()
                .mainAccountRegister(MainAccountRegister.starter(user, trader)).build();

        commandBus.handle(command);

        WebClient client = WebClient.builder().baseUrl("http://localhost:8080").build();
        HttpHeaders headers = Objects.requireNonNull(client.post().uri("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL).body(BodyInserters.fromFormData(createBodyValueForFillter(command)))
                .retrieve().toEntity(String.class).block()).getHeaders();

        return ResponseEntity.created(new URI("http:localhost:8080/api/mainaccounts"))
                .body(createRegisterResponse(headers));
    }

    private MainAccountRegisteredResponse createRegisterResponse(HttpHeaders headers) {
        var accesToken = "";
        if (!Objects.requireNonNull(headers.get("accessToken")).isEmpty()) {
            accesToken = Objects.requireNonNull(headers.get("accessToken")).get(0);
        }
        var refreshToken = "";
        if (!Objects.requireNonNull(headers.get("refreshToken")).isEmpty()) {
            refreshToken = Objects.requireNonNull(headers.get("refreshToken")).get(0);
        }
        return new MainAccountRegisteredResponse(accesToken, refreshToken);
    }

    private MultiValueMap<String, String> createBodyValueForFillter(MainAccountRegisterCommand command) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", command.getMainAccountRegister().userRegister().username());
        bodyValue.add("password", command.getMainAccountRegister().userRegister().password());
        return bodyValue;
    }
}
