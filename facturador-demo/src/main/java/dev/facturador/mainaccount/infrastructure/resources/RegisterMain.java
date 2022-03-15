package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.auth.domain.dto.LoginResponse;
import dev.facturador.mainaccount.domain.dto.RegisterResponse;
import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;
import dev.facturador.mainaccount.infrastructure.IMainAccountRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class RegisterMain {
    @Autowired
    private IMainAccountRegisterService mainAccountService;

    /**
     * Registra la cuenta principal en la base de datos
     * <br/>
     * Redirige al login y recupera de la respuesta el {@code Access Token} y {@code Refresh Token}
     *
     * @param tryRegister {@link RegisterRequest} Bussines Object para recibir el JSON
     * @return {@link HttpEntity} con el body de {@link LoginResponse}
     */
    @PostMapping("/mainaccounts")
    public HttpEntity<?> singup(@Valid @RequestBody RegisterRequest tryRegister) throws URISyntaxException {
        Collection<String> messages = mainAccountService.whenIndicesAreRepeatedReturnErrror(tryRegister);
        if (messages.size() >= 1) {
            return ResponseEntity.badRequest().body(messages);
        }
        mainAccountService.register(tryRegister);

        String username = tryRegister.getUserRegister().username();
        String password = tryRegister.getUserRegister().password();
        WebClient client = WebClient.builder().baseUrl("http://localhost:8080").build();
        HttpHeaders headers;
        headers = Objects.requireNonNull(client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(createBodyValueForFillter(username, password)))
                .retrieve().toEntity(String.class).block()).getHeaders();

        return ResponseEntity.created(new URI("http:localhost:8080/api/mainaccounts"))
                .body(createRegisterResponse(headers));
    }


    private RegisterResponse createRegisterResponse(HttpHeaders headers) {
        var accesToken = "";
        if (!Objects.requireNonNull(headers.get("Access-token")).isEmpty()) {
            accesToken = Objects.requireNonNull(headers.get("Access-token")).get(0);
        }
        var refreshToken = "";
        if (!Objects.requireNonNull(headers.get("Refresh-token")).isEmpty()) {
            refreshToken = Objects.requireNonNull(headers.get("Access-token")).get(0);
        }
        return new RegisterResponse(accesToken, refreshToken);
    }

    private MultiValueMap<String, String> createBodyValueForFillter(String usernameOrEmail, String password) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", usernameOrEmail);
        bodyValue.add("password", password);
        return bodyValue;
    }
}
