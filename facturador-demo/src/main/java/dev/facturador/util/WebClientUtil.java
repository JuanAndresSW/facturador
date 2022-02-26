package dev.facturador.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@RequiredArgsConstructor
public class WebClientUtil {
    private WebClient client;

    public WebClientUtil(WebClient client){
        this.client = client;
    }

    /**
     * Este metodo redirige una request a la url especificada en el WebClient /login y recibe su response
     *
     * @param bodyValue Valor del Body
     * @return Envia los headers de la respuesta
     */
    public HttpHeaders responseHeadersToLogin(MultiValueMap<String, String> bodyValue) {
        log.info("--ENTRE A RECUPERAR LOS HEADERS---");
        return client.post().uri("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.ALL)
                        .body(BodyInserters.fromFormData(bodyValue))
                        .retrieve().toEntity(String.class).block().getHeaders();
    }

    /**
     * Transforma los datos a datos de tipo application/x-www-form-urlencoded
     * Esto para que el metodo de login lo pueda recibir
     *
     * @param usernameOrEmail Credencial de inicio de sesion
     * @param password        Contraseña de inicio de sesion
     * @return retorna el body
     */
    public MultiValueMap<String, String> buildValueLogin(String usernameOrEmail, String password) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        log.info("---ENTRE A CREAR EL BODY VALUE---");
        bodyValue.add("usernameOrEmail", usernameOrEmail);
        bodyValue.add("password", password);
        log.info("---RETORNO EL BODY VALUE---");
        return bodyValue;
    }
}
