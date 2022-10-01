package dev.facturador.security.infrastructure.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.security.domain.CustomUserDetails;
import dev.facturador.security.infrastructure.adapters.CustomJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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

/**
 * Es el filtro de autorización decide si la ruta que quieres entrar necesita autorizacion o no
 * y si necesita autorización decide si darla o no
 */
@Slf4j
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final CustomJWT jwt;
    private final UserDetailsService service;

    @Autowired
    public CustomAuthorizationFilter(CustomJWT jwt, UserDetailsService service) {
        this.jwt = jwt;
        this.service = service;
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
        if(isNotRequiredAuthorization(request)){
            filterChain.doFilter(request, response);
        } else {
            log.info("Necesito autenticarme");
            String authHeader = request.getHeader(AUTHORIZATION);
            var token = jwt.verifyToken(authHeader);
            log.info("EL TOKEN ES: {}", token);
            if (StringUtils.hasText(token)) {
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
        /*
        if (isRequiredAuthorization(request)) {    }
        */
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     */
    private void setUpSpringAuthentication(String token, HttpServletRequest request) {
        log.info("Llegue al metodo de autenticacion");
        var email = jwt.getTokenSubject(token);
        log.info("El email es: {}", email);
        var user = (CustomUserDetails)service.loadUserByUsername(email);
        log.info("Pase el casteo de UserDetail");
        var authUser = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
       log.info("Cree el authUser");
        authUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authUser);
    }

    private boolean isNotRequiredAuthorization(HttpServletRequest request) {
        return request.getServletPath().equals("/login") ||
                request.getServletPath().equals("/api/auth/accounts/log-in") ||
                request.getServletPath().equals("/api/auth/accounts") ||
                request.getServletPath().equals("/api/auth/refresh");
    }
    /*
    private boolean isRequiredAuthorization(HttpServletRequest request) {
        return !request.getServletPath().equals("/login") &&
                !request.getServletPath().equals("/api/auth/accounts/log-in") &&
                !request.getServletPath().equals("/api/auth/accounts") &&
                !request.getServletPath().equals("/api/auth/refresh");
    }
*/
}
