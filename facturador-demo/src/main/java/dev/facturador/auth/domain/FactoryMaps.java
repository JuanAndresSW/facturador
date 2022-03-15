package dev.facturador.auth.domain;

import dev.facturador.auth.domain.bo.LoginRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class FactoryMaps {

    /**
     * Este metodo recibe el JSON con el {@link LoginRequest} y lo transforma a <br/>
     * {@link MultiValueMap} Esto es para que se puede traducir a {@code application/x-www-form-urlencoded}
     *
     * @param tryLogin JSON {@link LoginRequest}
     * @return {@link MultiValueMap}
     */
    public MultiValueMap<String, String> translateJsonToValueMap(LoginRequest tryLogin) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", tryLogin.usernameOrEmail());
        bodyValue.add("password", tryLogin.password());

        return bodyValue;
    }

    public HashMap<String, String> createTokenResponse(String accesToken, String refreshToken) {
        var tokens = new HashMap<String, String>();
        tokens.put("Access-Token", accesToken);
        tokens.put("Refresh-Token", refreshToken);

        return tokens;
    }

    public Map<String, String> createDataFromHeaders(HttpHeaders headers) {
        var data = new HashMap<String, String>();
        if (!headers.get("Access-token").isEmpty()) {
            data.put("access", Objects.requireNonNull(headers.get("Access-token")).get(0));
        }
        if (!headers.get("Refresh-token").isEmpty()) {
            data.put("refresh", Objects.requireNonNull(headers.get("Refresh-token")).get(0));
        }
        if (!headers.get("user-data").isEmpty()) {
            data.put("username", Objects.requireNonNull(headers.get("user-data")).get(0));
        }
        if (!headers.get("user-data").isEmpty()) {
            data.put("rol", Objects.requireNonNull(headers.get("user-data")).get(1));
        }
        return data;
    }

}
