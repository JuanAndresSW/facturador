package dev.facturador.mainaccount.infrastructure;

import dev.facturador.auth.domain.dto.LoginResponse;
import dev.facturador.mainaccount.domain.bo.RegisterBo;
import dev.facturador.mainaccount.domain.dto.RegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class MainAccountController {
    private static final String REGISTER_MAIN = "/auth/mainaccounts";
    @Autowired
    private IMainAccountService mainAccountService;

    /**
     * Registra la cuenta principal en la base de datos
     * <br/>
     * Redirige al login y recupera de la respuesta el {@code Access Token} y {@code Refresh Token}
     *
     * @param tryRegister {@link RegisterBo} Bussines Object para recibir el JSON
     * @return {@link HttpEntity} con el body de {@link LoginResponse}
     */
    @PostMapping(REGISTER_MAIN)
    public HttpEntity singup(@Valid @RequestBody RegisterBo tryRegister) throws URISyntaxException {
        String message = mainAccountService.whenIndicesAreRepeatedReturnErrror(tryRegister);
        if (StringUtils.hasText(message)) {
            return ResponseEntity.badRequest().body(message);
        }
        mainAccountService.register(tryRegister);

        var headers = callFilterLogin(tryRegister.getUserBo().username(), tryRegister.getUserBo().password());
        String accesToken = headers.get("Access-token").get(0);
        String refreshToken = headers.get("Refresh-token").get(0);
        return ResponseEntity.created(new URI("http:localhost:8080/api/auth/mainaccounts")).body(new RegisterResponse(accesToken, refreshToken));
    }

    private HttpHeaders callFilterLogin(String usernameOrEmail, String password) {
        WebClient client = WebClient.builder().baseUrl("http://localhost:8080").build();
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", usernameOrEmail);
        bodyValue.add("password", password);

        return client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(bodyValue))
                .retrieve().toEntity(String.class).block().getHeaders();
    }
}
