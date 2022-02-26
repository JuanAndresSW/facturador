package dev.facturador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.bo.LoginBo;
import dev.facturador.bo.security.CustomUserDetails;
import dev.facturador.dto.IApiResponse;
import dev.facturador.dto.LoginResponse;
import dev.facturador.filter.CustomAuthenticationFilter;
import dev.facturador.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private static final String LOGIN = "/login";
    private static final String REFRESH_TOKEN = "/refresh";
    private static final String INIT_APP = "/init";

    /**
     * Este metodo recibe el JSON con el {@link LoginBo}, este lo transforma a <br/>
     * {@code application/x-www-form-urlencoded} esto lo hace con la clase {@link MultiValueMap}
     * <br/>
     * Con el {@link WebClient} envia otra request a {@link CustomAuthenticationFilter}
     * <br/>
     * De {@link CustomAuthenticationFilter} se recupera los headers y se crea el {@link LoginResponse}
     *
     * @param tryLogin Es {@link LoginBo} Bussines Object preparado para reciber el JSON
     * @return {@link ResponseEntity} con el body de {@link LoginResponse}
     */
    @PostMapping(LOGIN)
    public HttpEntity<? extends IApiResponse> login(@Valid @RequestBody LoginBo tryLogin) {
        MultiValueMap<String, String> bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("usernameOrEmail", tryLogin.usernameOrEmail());
        bodyValue.add("password", tryLogin.password());

        var client = WebClient.builder().baseUrl("http://localhost:8080").build();
        var headers = Objects.requireNonNull(client.post().uri("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.ALL)
                .body(BodyInserters.fromFormData(bodyValue))
                .retrieve().toEntity(String.class).block()).getHeaders();

        var data = getDataFromHeader(headers);
        var response = createLoginResponse(headers, data);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Este metodo es simplemente pasa por el filtro para comprobar que el {@code Access-Token} sea valido
     */
    @PostMapping(INIT_APP)
    public HttpEntity<String> initApp() {
        return ResponseEntity.ok().body("Success");
    }

    /**
     * Actulizo el {@code Bearer Access-Token} con el {@code Refresh-Token} si es valido
     *
     * @param request  Objeto {@link HttpServletRequest} es la request como tal
     * @param response Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     */
    @PostMapping(REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        var jwt = new JWTUtil();
        if (jwt.verifyAuthToken(authHeader)) {
                var user = jwt.createUserAuthenticatedByRefreshToken(authHeader, response);
                var URL = request.getRequestURI().toString();
                String accesToken = jwt.createAccesToken(user.getUsername(), user.getAuthorities().stream().toList().get(0).getAuthority(), URL);
                String refreshToken = jwt.createRefreshToken(user.getUsername(), URL);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("Access-Token", accesToken);
                tokens.put("Refresh-Token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } else {
            throw new RuntimeException("Refresh token is mising");
        }
    }


    private Map<String, String> getDataFromHeader(HttpHeaders headers) {
        var values = new HashMap<String, String>();

        values.put("access", Objects.requireNonNull(headers.get("Access-token")).get(0));
        values.put("refresh", Objects.requireNonNull(headers.get("Refresh-token")).get(0));
        values.put("username", Objects.requireNonNull(headers.get("user-data")).get(0));
        values.put("rol", Objects.requireNonNull(headers.get("user-data")).get(1));

        return values;

    }

    private LoginResponse createLoginResponse(HttpHeaders headers, Map<String, String> data) {
        if (data.get("rol").equals("MAIN")) {
            String active = headers.get("user-data").get(2);
            String passive = headers.get("user-data").get(3);
            if (StringUtils.hasText(active) && StringUtils.hasText(passive)) {
                log.info("--RESPONSE TIENE ACTIVE Y PASSIVE---");
                return new LoginResponse(data.get("username"), parseInt(active), parseInt(passive), data.get("access"), data.get("refresh"));
            }
        }
        return new LoginResponse(data.get("username"), data.get("access"), data.get("refresh"));
    }
}
