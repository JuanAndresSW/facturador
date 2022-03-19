package dev.facturador.auth.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.auth.domain.CustomUserDetails;
import dev.facturador.auth.domain.FactoryMaps;
import dev.facturador.auth.domain.bo.LoginRequest;
import dev.facturador.auth.domain.dto.LoginResponse;
import dev.facturador.auth.infrastructure.CustomAuthenticationFilter;
import dev.facturador.shared.infrastructure.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthUtil {
    @Autowired
    private UserDetailsService serviceCustomUerDetails;
    @Autowired
    private FactoryMaps generator;

    /**
     * Crea el LoginResponse con la informacion de los headers
     *
     * @param headers {@link HttpHeaders}
     * @return El dto {@link LoginResponse}
     */
    public LoginResponse createLoginResponseWithHeaders(HttpHeaders headers) {
        var data = generator.createDataFromHeaders(headers);
        if (data.get("role").equals("MAIN")) {
            String active = headers.get("user-data").get(2);
            String passive = headers.get("user-data").get(3);
            if (StringUtils.hasText(active) && StringUtils.hasText(passive)) {
                return new LoginResponse(
                        data.get("username"),
                        data.get("role"),
                        parseInt(active),
                        parseInt(passive),
                        data.get("accessToken"),
                        data.get("refreshToken"));
            }
        }
        return new LoginResponse(data.get("username"), data.get("role"), data.get("accessToken"), data.get("refreshToken"));
    }

    /**
     * Recibe el value de {@code translateJsonToValueMap} <br/>
     * Envio otra request a {@code http://localhost:8080/login} a la clase {@link CustomAuthenticationFilter}
     *
     * @param tryLogin Informacion de login en crudo
     * @return {@link ResponseEntity} con la respuesta de la request
     */
    public HttpEntity<String> callLogin(LoginRequest tryLogin) {
        var values = generator.translateJsonToValueMap(tryLogin);
        var client = WebClient.builder().baseUrl("http://localhost:8080").build();
        return client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(values))
                .retrieve().toEntity(String.class).block();
    }

    /**
     * Crea un usuaro autenticado con {@link JWT} {@code createUserByToken} <br/>
     * Recupero el {@link CustomUserDetails} de este usuario
     *
     * @param authHeader Bearer Token recuperado del header
     * @param jwt        {@link JWT} para las utilidades
     * @param response   {@link HttpServletResponse} este parametro sirbe para controlar si este metodo causa una excepcion
     * @return Envia un {@link CustomUserDetails}
     */
    public CustomUserDetails creteUserWithToken(String authHeader, JWT jwt, HttpServletResponse response) throws IOException {
        try {
            if (jwt.verifyToken(authHeader)) {
                var token = authHeader.substring("Bearer ".length());
                var username = jwt.createDecoder(token).getSubject();

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
        throw new RuntimeException("Se rompio todo");
    }

    public HashMap<String, String> bringBackMapOfTokens(String access, String refresh) {
        return generator.createTokenResponse(access, refresh);
    }

}
