package dev.facturador.global.infrastructure.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.global.infrastructure.adapters.CustomJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
/**Es el filtro de autorización decide si la ruta que quieres entrar necesita autorizacion o no
 * y si necesita autorización decide si darla o no*/
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final CustomJWT jwt;

    public CustomAuthorizationFilter(CustomJWT jwt) {
        this.jwt = jwt;
    }

    /**
     * Decide si la request ingresa (Autoriza) a la API o es rechazada
     *
     * @param request     Parámetro para que maneja la request
     * @param response    Parametro para marcar la respuesta de la request
     * @param filterChain Enviá el resultado del filtro
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (isNotRequiredAuthorization(request)) {
            filterChain.doFilter(request, response);
        }
        if (isRequiredAuthorization(request)) {
            String authHeader = request.getHeader(AUTHORIZATION);
            if (jwt.verifyToken(authHeader)) {
                try {
                    setUpSpringAuthentication(authHeader, request);

                } catch (Exception ex) {
                    response.setHeader("error", ex.getMessage());
                    response.setStatus(UNAUTHORIZED.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error-message", ex.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     */
    private void setUpSpringAuthentication(String authHeader, HttpServletRequest request) {

        var email = jwt.createUserByToken(authHeader);
        var authUser = new UsernamePasswordAuthenticationToken(email, null, null);
        authUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authUser);
    }

    private boolean isNotRequiredAuthorization(HttpServletRequest request) {
        return request.getServletPath().equals("/login") ||
                request.getServletPath().equals("/api/auth/accounts/log-in") ||
                request.getServletPath().equals("/api/auth/accounts") ||
                request.getServletPath().equals("/api/auth/refresh");
    }

    private boolean isRequiredAuthorization(HttpServletRequest request) {
        return !request.getServletPath().equals("/login") &&
                !request.getServletPath().equals("/api/auth/accounts/log-in") &&
                !request.getServletPath().equals("/api/auth/accounts") &&
                !request.getServletPath().equals("/api/auth/refresh");
    }

}
