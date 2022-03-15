package dev.facturador.auth.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.auth.application.AuthUtil;
import dev.facturador.auth.application.JWTOfAuth;
import dev.facturador.auth.domain.bo.LoginRequest;
import dev.facturador.auth.domain.dto.InitResponse;
import dev.facturador.auth.domain.dto.LoginResponse;
import dev.facturador.shared.infrastructure.JWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private static final String LOGIN = "/login";
    private static final String REFRESH_TOKEN = "/refresh";
    private static final String INIT_APP = "/init";

    @Autowired
    private AuthUtil util;

    /**
     * Este metodo recupero los headers de la respuesta de {@code callFilter}
     * Crea un {@link LoginResponse} con la respuesta de lo headers
     *
     * @param tryLogin Es {@link LoginRequest} Bussines Object preparado para reciber el JSON
     * @return {@link ResponseEntity} con el body de {@link LoginResponse}
     */
    @PostMapping(LOGIN)
    public HttpEntity createLoginResponse(@Valid @RequestBody LoginRequest tryLogin) {
        var headers = util.callLogin(tryLogin).getHeaders();
        var response = util.createLoginResponseWithHeaders(headers);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Este metodo verifica si la sesion activa es valida caso correcto
     * Se enviarian los datos necesarios para la sesion
     *
     * @return
     */
    @GetMapping(INIT_APP)
    public void initApp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        JWT jwt = new JWTOfAuth();
        var user = util.creteUserWithToken(authHeader, jwt, response);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getOutputStream(), new InitResponse(
                                user.getUsername(),
                                user.getAuthorities().stream().toList().get(0).getAuthority(),
                                user.getActive(),
                                user.getPassive()));
    }

    /**
     * Actulizo el {@code Access-Token} con el {@code Refresh-Token}
     *
     * @param request  Objeto {@link HttpServletRequest} recibe la request
     * @param response Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     */
    @PostMapping(REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authHeader = request.getHeader(AUTHORIZATION);
        JWT jwt = new JWTOfAuth();
        var userDetails = util.creteUserWithToken(authHeader, jwt, response);

        String URL = request.getRequestURI().toString();
        var rol = userDetails.getAuthorities();
        String username = userDetails.getUsername();

        var tokens = util.bringBackMapOfTokens(
                jwt.createAccesToken(username, rol, URL),
                jwt.createRefreshToken(username, URL));

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
