package dev.facturador.controllers;

import dev.facturador.bo.LoginBo;
import dev.facturador.dto.IApiResponse;
import dev.facturador.dto.LoginResponse;
import dev.facturador.filter.CustomAuthenticationFilter;
import dev.facturador.util.WebClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public final class AuthController {
    private static final String LOGIN = "/login";
    private static final String REFRESH_TOKEN = "/refresh";

    /**
     * Este metodo recibe el JSON con el {@link LoginBo}, este lo transforma a <br/>
     * {@code application/x-www-form-urlencoded}
     * <br/>
     * Y se envia otra request a  {@link CustomAuthenticationFilter}
     * <br/>
     * Con los headers del response de {@link CustomAuthenticationFilter} se crea el {@link LoginResponse}
     * @param tryLogin Es {@link LoginBo} Bussines Object preparado para reciber el JSON
     * @return {@link ResponseEntity} con el body de {@link LoginResponse}
     */
    @PostMapping(LOGIN)
    public HttpEntity<? extends IApiResponse> login(@Valid @RequestBody LoginBo tryLogin) {
        //Recupero los header de la respuesta recibida
        var webUtil = new WebClientUtil(WebClient.builder().baseUrl("http://localhost:8080").build());
        var headers = webUtil.responseHeadersToLogin(webUtil.buildValueLogin
                (tryLogin.usernameOrEmail(), tryLogin.password()));

        var data = getDataFromHeader(headers);
        var response = createLoginResponse(headers, data);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Este metodo es simplemente pasa por el filtro para comprobar que el {@code Access-Token} sea valido
     */
    @PostMapping()
    public HttpEntity<String> initApp() {
        return ResponseEntity.ok().body("Success");
    }

    /**
     * Actulizo el {@code Bearer Access-Token} con el {@code Refresh-Token} si es valido
     *
     * @param request Objeto {@link HttpServletRequest} es la request como tal
     * @param response Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     */
    @PostMapping(REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(AUTHORIZATION);

    }


    private Map<String, String> getDataFromHeader(HttpHeaders headers) {
        var values = new HashMap<String, String>();
        values.put("access", headers.get("Access-token").get(0));
        values.put("refresh", headers.get("Refresh-token").get(0));
        values.put("username", headers.get("user-data").get(0));
        values.put("rol", headers.get("user-data").get(1));
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
