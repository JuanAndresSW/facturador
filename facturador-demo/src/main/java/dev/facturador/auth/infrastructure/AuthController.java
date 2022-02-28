package dev.facturador.auth.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.auth.application.AuthUtil;
import dev.facturador.auth.domain.bo.LoginBo;
import dev.facturador.auth.domain.dto.LoginResponse;
import dev.facturador.gategay.responsecore.IApiResponse;
import dev.facturador.infrastructurecore.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
     * @param tryLogin Es {@link LoginBo} Bussines Object preparado para reciber el JSON
     * @return {@link ResponseEntity} con el body de {@link LoginResponse}
     */
    @PostMapping(LOGIN)
    public HttpEntity<? extends IApiResponse> createResponse(@Valid @RequestBody LoginBo tryLogin) {
        var headers = util.callFilter(tryLogin).getHeaders();
        var response = util.createLoginResponseWithHeaders(headers);

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
     * Actulizo el {@code Access-Token} con el {@code Refresh-Token}
     *
     * @param request  Objeto {@link HttpServletRequest} es la request como tal
     * @param response Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     */
    @PostMapping(REFRESH_TOKEN)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authHeader = request.getHeader(AUTHORIZATION);
        var jwt = new JWTUtil();
        var userDetails = util.creteUserWithToken(authHeader, jwt, response);

        String URL = request.getRequestURI().toString();
        String rol = userDetails.getAuthorities().stream().toList().get(0).getAuthority();
        String username = userDetails.getUsername();

        var tokens = util.createTokenResponse(
                jwt.createAccesToken(username, rol, URL),
                jwt.createRefreshToken(username, rol, URL));

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
