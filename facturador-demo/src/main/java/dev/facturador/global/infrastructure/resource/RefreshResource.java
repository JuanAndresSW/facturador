package dev.facturador.global.infrastructure.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.security.domain.CustomUserDetails;
import dev.facturador.global.infrastructure.adapters.CustomJWT;
import dev.facturador.security.infrastructure.adapter.CustomUserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * EndPoint para refrescar los Tokens
 */
@RestController
@RequestMapping(path = "/api/auth")
public class RefreshResource {
    private final CustomJWT jwt;
    private final CustomUserDetailsService service;

    public RefreshResource(CustomUserDetailsService service, CustomJWT jwt) {
        this.service = service;
        this.jwt = jwt;
    }

    /**
     * Se llama cuando el {@code Access-Token} ha expirado
     * Crea un nuevo {@code Access-Token y Refresh-Token} con el {@code Refresh-Token} anterior
     *
     * @param request  {@link HttpServletRequest} de la API de HttpServlet. Maneja la request
     * @param response {@link HttpServletResponse} de la API HttpServlet. Marca la respuesta de la request
     */
    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authHeader = request.getHeader(AUTHORIZATION);
        var userDetails = this.creteUserWithToken(authHeader, response);

        String URL = request.getRequestURI();
        String email = userDetails.getEmail();

        var tokens = new HashMap<String, String>();
        tokens.put("accessToken", jwt.createAccesToken(email, URL));
        tokens.put("refreshToken", jwt.createRefreshToken(email, URL));

        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    /**
     * Recupero el {@link CustomUserDetails} con el Token CustomJWT
     */
    private CustomUserDetails creteUserWithToken(String authHeader, HttpServletResponse response) throws IOException {
        try {
            var token = jwt.token(authHeader);
            if (StringUtils.hasText(token)) {
                var email = jwt.createUserByToken(token);
                return (CustomUserDetails) service.loadUserByUsername(email);
            }
        } catch (Exception ex) {
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error-message", ex.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        throw new RuntimeException("Refresh token is mising");
    }

}
