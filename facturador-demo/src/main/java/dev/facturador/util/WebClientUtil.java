package dev.facturador.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


public final class WebClientUtil {


    /**
     * Este metodo redirige una request a la url especificada en el WebClient /login y recibe su response
     *
     * @param bodyValue Valor del Body
     * @return Envia los headers de la respuesta
     */
    public static HttpHeaders responseHeadersToLogin(MultiValueMap<String, String> bodyValue, WebClient client) {
        return client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(bodyValue))
                .retrieve().toEntity(String.class).block()
                .getHeaders();
    }

    /**
     * Transforma los datos a datos de tipo application/x-www-form-urlencoded
     * Esto para que el metodo de login lo pueda recibir
     *
     * @param usernameOrEmail Credencial de inicio de sesion
     * @param password        Contraseña de inicio de sesion
     * @return retorna el body
     */
    public static MultiValueMap<String, String> buildValueLogin(String usernameOrEmail, String password) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", usernameOrEmail);
        bodyValue.add("password", password);

        return bodyValue;
    }
}
