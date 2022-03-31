package dev.facturador.shared.infrastructure.auth.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.shared.domain.CustomUserDetails;
import dev.facturador.shared.infrastructure.CustomJWT;
import dev.facturador.shared.infrastructure.auth.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class RefreshResource {
    private CustomJWT jwt;
    private CustomUserDetailsService service;

    public RefreshResource(CustomUserDetailsService service, CustomJWT jwt){
        this.service = service;
        this.jwt = jwt;
    }
    /**
     * Actuliza el {@code Access-Token} con el {@code Refresh-Token}
     *
     * @param request  Objeto {@link HttpServletRequest} recibe la request
     * @param response Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     */
    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authHeader = request.getHeader(AUTHORIZATION);
        var userDetails = this.creteUserWithToken(authHeader, response);

        String URL = request.getRequestURI().toString();
        var rol = userDetails.getAuthorities();
        String email = userDetails.getEmail();

        var tokens = this.createTokenResponse(
                jwt.createAccesToken(email, rol, URL),
                jwt.createRefreshToken(email, URL));

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    /**
     * Recupero el {@link CustomUserDetails} con el Token CustomJWT
     */
    private CustomUserDetails creteUserWithToken(String authHeader, HttpServletResponse response) throws IOException {
        try {
            if (jwt.verifyToken(authHeader)) {
                var token = authHeader.substring("Bearer ".length());
                var email = jwt.createDecoder(token).getSubject();

                return (CustomUserDetails) service.loadUserByUsername(email);
            }
        } catch (Exception ex) {
            log.error("Error refresh in: {}", ex.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error-message", ex.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        throw new RuntimeException("Refresh token is mising");
    }

    private HashMap<String, String> createTokenResponse(String accesToken, String refreshToken) {
        var tokens = new HashMap<String, String>();
        tokens.put("accessToken", accesToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

}
