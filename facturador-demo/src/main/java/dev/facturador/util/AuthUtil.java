package dev.facturador.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.bo.LoginBo;
import dev.facturador.bo.security.CustomUserDetails;
import dev.facturador.dto.LoginResponse;
import dev.facturador.filter.CustomAuthenticationFilter;
import dev.facturador.services.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthUtil {
    @Autowired
    private CustomUserDetailsService serviceCustomUerDetails;

    /**
     * Recupera la informacion guardada en los headers
     * @param headers
     * @return
     */
    public LoginResponse createLoginResponseWithHeaders(HttpHeaders headers) {
        var data = new HashMap<String, String>();

        data.put("access", Objects.requireNonNull(headers.get("Access-token")).get(0));
        data.put("refresh", Objects.requireNonNull(headers.get("Refresh-token")).get(0));
        data.put("username", Objects.requireNonNull(headers.get("user-data")).get(0));
        data.put("rol", Objects.requireNonNull(headers.get("user-data")).get(1));

        if (data.get("rol").equals("MAIN")) {
            String active = headers.get("user-data").get(2);
            String passive = headers.get("user-data").get(3);
            if (StringUtils.hasText(active) && StringUtils.hasText(passive)) {

                return new LoginResponse(data.get("username"), parseInt(active), parseInt(passive), data.get("access"), data.get("refresh"));
            }
        }
        return new LoginResponse(data.get("username"), data.get("access"), data.get("refresh"));
    }

    /**
     * Este metodo recibe el JSON con el {@link LoginBo} y lo transforma a <br/>
     * {@code application/x-www-form-urlencoded} esto lo hace con la clase {@link MultiValueMap}
     *
     * @param tryLogin JSON {@link LoginBo}
     * @return {@link MultiValueMap}
     */
    public MultiValueMap<String, String> translateJsonToValueMap(LoginBo tryLogin) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", tryLogin.usernameOrEmail());
        bodyValue.add("password", tryLogin.password());

        return bodyValue;
    }

    /**
     *  Recibe el value de {@code translateJsonToValueMap} <br/>
     *  Envio otra request a {@code http://localhost:8080/login} a la clase {@link CustomAuthenticationFilter}
     * @param tryLogin Informacion de login en crudo
     * @return {@link ResponseEntity} con la respuesta de la request
     */
    public HttpEntity<String> callFilter(LoginBo tryLogin){
        var values = translateJsonToValueMap(tryLogin);
        log.info("---LOS VALORES PASARON VIEN---");
        var client = WebClient.builder().baseUrl("http://localhost:8080").build();
        log.info("---WEB CLIENT PASO BIEN---");
        return client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(values))
                .retrieve().toEntity(String.class).block();
    }

    /**
     *  Crea un usuaro autenticado con {@link JWTUtil} {@code createUserByToken} <br/>
     *  Recupero el {@link CustomUserDetails} de este usuario
     * @param authHeader Bearer Token recuperado del header
     * @param jwt {@link JWTUtil} para las utilidades
     * @param response {@link HttpServletResponse} este parametro sirbe para controlar si este metodo causa una excepcion
     * @return Envia un {@link CustomUserDetails}
     */
    public CustomUserDetails creteUserWithToken(String authHeader, JWTUtil jwt, HttpServletResponse response) throws Exception {
        try {
            if (jwt.verifyAuthToken(authHeader)) {
                String token = authHeader.substring("Bearer ".length());
                var decodedJWT = jwt.createDecoder(token);
                String username = jwt.getSubject(decodedJWT);

                return (CustomUserDetails) serviceCustomUerDetails.loadUserByUsername(username);
            } else {
                throw new RuntimeException("Refresh token is mising");
            }
        } catch (Exception ex) {
            log.error("Error logging in: {}", ex.getMessage());
            response.setHeader("error", ex.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error-message", ex.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);

            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        throw new Exception("Se rompio todo");
    }

    public HashMap<String, String> createTokenResponse(String accesToken, String refreshToken){
        var tokens = new HashMap<String, String>();
        tokens.put("Access-Token", accesToken);
        tokens.put("Refresh-Token", refreshToken);

        return tokens;
    }
}
