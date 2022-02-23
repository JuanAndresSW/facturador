package dev.facturador.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static dev.facturador.util.AuthorizationUtil.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwt;

    public CustomAuthorizationFilter(JWTUtil jwt){
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //Si es algunas de estas rutas no necesita autorizacion solo esta intentando iniciar o registrarse
        if (checkIfAuthorizationIsNotRequired(request)){
            filterChain.doFilter(request, response);
        }
        //De cualquier otra ruta que no sean esas si se comprueba la autorizacion
        if (checkIfAuthorizationIsRequired(request)) {
            String authHeader = request.getHeader(AUTHORIZATION);
            if(jwt.verifyAuthToken(authHeader)){
                try {
                    String token = authHeader.substring("Bearer ".length());
                    var authUser = tokenAuthorizedUser(token, jwt);
                    SecurityContextHolder.getContext().setAuthentication(authUser);
                    filterChain.doFilter(request, response);
                } catch (Exception ex){
                    //Propago el mensaje de error
                    log.error("Error logging in: {}", ex.getMessage());
                    response.setHeader("error", ex.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error-message", ex.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }

            } else {
                filterChain.doFilter(request, response);
            }

        }
    }

}
