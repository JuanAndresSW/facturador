package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.auth.domain.response.LoginResponse;
import dev.facturador.mainaccount.domain.dto.RegisterResponse;
import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;
import dev.facturador.mainaccount.infrastructure.service.IMainAccountRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
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
     * Envia los datos necesarios para Iniciar sesion
     *
     * @param tryRegister {@link RegisterRequest} Bussines Object para recibir el JSON
     * @return {@link HttpEntity} con el body de {@link LoginResponse}
     */
    @PostMapping("/mainaccounts")
    public HttpEntity<?> singup(@Valid @RequestBody RegisterRequest tryRegister) throws URISyntaxException {
        var message = mainAccountService.whenIndicesAreRepeatedReturnErrror(tryRegister);
        if (StringUtils.hasText(message)) {
            return ResponseEntity.badRequest().body(message);
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
        if (!Objects.requireNonNull(headers.get("accessToken")).isEmpty()) {
            accesToken = Objects.requireNonNull(headers.get("accessToken")).get(0);
        }
        var refreshToken = "";
        if (!Objects.requireNonNull(headers.get("refreshToken")).isEmpty()) {
            refreshToken = Objects.requireNonNull(headers.get("refreshToken")).get(0);
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
