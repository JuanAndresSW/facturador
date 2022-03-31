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
        var data = this.extractDataFromHeader(headers);
        if (data.get("role").equals("MAIN")) {
            String active = headers.get("user-data").get(2);
            String passive = headers.get("user-data").get(3);
            return new LoginResponse(
                    data.get("username"),
                    data.get("role"),
                    parseInt(active),
                    parseInt(passive),
                    data.get("accessToken"),
                    data.get("refreshToken"));
        }
        return new LoginResponse(data.get("username"), data.get("role"), data.get("accessToken"), data.get("refreshToken"));
    }

    private Map<String, String> extractDataFromHeader(HttpHeaders headers) {
        var data = new HashMap<String, String>();
        if (!headers.get("accessToken").isEmpty()) {
            data.put("accessToken", Objects.requireNonNull(headers.get("accessToken")).get(0));
        }
        if (!headers.get("refreshToken").isEmpty()) {
            data.put("refreshToken", Objects.requireNonNull(headers.get("refreshToken")).get(0));
        }
        if (!headers.get("user-data").isEmpty()) {
            data.put("username", Objects.requireNonNull(headers.get("user-data")).get(0));
            data.put("role", Objects.requireNonNull(headers.get("user-data")).get(1));
        }
        return data;
    }

}
