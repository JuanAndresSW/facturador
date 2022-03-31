package dev.facturador.shared.infrastructure.auth.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.shared.domain.CustomUserDetails;
import dev.facturador.shared.domain.InitResponse;
import dev.facturador.shared.infrastructure.CustomJWT;
import dev.facturador.shared.infrastructure.auth.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class InitResource {
    private CustomJWT jwt;
    private CustomUserDetailsService service;

    public InitResource(CustomUserDetailsService service, CustomJWT jwt){
        this.service = service;
        this.jwt = jwt;
    }

    /**
     * Este metodo envia los datos necesarios para el inicio de la App
     */
    @GetMapping("/init")
    public void initApp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authToken = request.getHeader(AUTHORIZATION);
        var initResponse = this.createResponseWithToken(authToken, response);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getOutputStream(), initResponse);
    }

    /**
     * Crea el InitResponse con los datos del AuthToken
     * Este Response varia segun si es Main o Branch
     *
     * @param authToken Bearer Token CustomJWT
     * @param response  response de la Api {@link HttpServletResponse}
     * @return {@link InitResponse}
     */
    private InitResponse createResponseWithToken(String authToken, HttpServletResponse response) throws IOException {
        try {
            if (jwt.verifyToken(authToken)) {
                var token = authToken.substring("Bearer ".length());
                var email = jwt.createDecoder(token).getSubject();
                var user = ((CustomUserDetails) service.loadUserByUsername(email));
                var role = user.getAuthorities().stream().toList().get(0).getAuthority();
                if (role.equals("MAIN")) {
                    return new InitResponse(user.getUsername(), role, user.getActive(), user.getPassive());
                }
                if (role.equals("BRANCH")) {
                    return new InitResponse(user.getUsername(), role, null, null);
                }
            }
        } catch (Exception ex) {
            log.error("Error init in: {}", ex.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error-message", ex.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
        throw new RuntimeException("Refresh token is mising");
    }
}
