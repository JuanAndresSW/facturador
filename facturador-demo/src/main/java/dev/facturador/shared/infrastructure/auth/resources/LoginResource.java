package dev.facturador.shared.infrastructure.auth.resources;

import dev.facturador.shared.domain.LoginRequest;
import dev.facturador.shared.domain.LoginResponse;
import lombok.extern.slf4j.Slf4j;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class LoginResource {

    /**
     * Este metodo recibe los headers de {@code callLogin} el cual llama al Login
     * Y crea un {@link LoginResponse} para la respuesta del Login
     *
     * @param tryLogin Es {@link LoginRequest} Bussines Object preparado para reciber el JSON
     * @return {@link ResponseEntity} con el body de {@link LoginResponse}
     */
    @PostMapping("/login")
    public HttpEntity loginWithJSON(@Valid @RequestBody LoginRequest tryLogin) {
        var headers = this.callLogin(tryLogin).getHeaders();
        var response = this.formTheLoginResponseWithHeaders(headers);

        return ResponseEntity.ok().body(response);
    }

    private HttpEntity<String> callLogin(LoginRequest tryLogin) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", tryLogin.usernameOrEmail());
        bodyValue.add("password", tryLogin.password());

        var client = WebClient.builder().baseUrl("http://localhost:8080").build();
        return client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(bodyValue))
                .retrieve().toEntity(String.class).block();
    }

    private LoginResponse formTheLoginResponseWithHeaders(HttpHeaders headers) {
        if (headers.get("role").get(0).equals("MAIN")) {
            String active = headers.get("active").get(0);
            String passive = headers.get("pasive").get(0);
            String IDTrader = headers.get("IDTrader").get(0);
            return new LoginResponse(
                    headers.get("username").get(0),
                    parseLong(IDTrader),
                    headers.get("role").get(0),
                    parseInt(active),
                    parseInt(passive),
                    headers.get("accessToken").get(0),
                    headers.get("refreshToken").get(0));
        }

        return new LoginResponse(headers.get("username").get(0), headers.get("role").get(0), headers.get("accessToken").get(0), headers.get("refreshToken").get(0));
    }


}
