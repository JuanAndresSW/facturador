package dev.facturador.security.infrastructure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.security.domain.exception.ResourceNotFound;
import dev.facturador.security.infrastructure.adapter.CustomJWT;
import dev.facturador.security.domain.CustomUserDetails;
import dev.facturador.security.infrastructure.adapter.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final CustomJWT jwt;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public CustomAuthorizationFilter(CustomJWT jwt, CustomUserDetailsService userDetailsService) {
        this.jwt = jwt;
        this.userDetailsService = userDetailsService;
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

        if (isRequiredAuthorization(request) ) {
            log.info("Entro al filtro?");
            log.info("La URL que entro al filtro es: {}", request.getRequestURI());
            String authHeader = request.getHeader(AUTHORIZATION);
            var token = jwt.token(authHeader);
            try {
                setUpSpringAuthentication(token, request);
            } catch (Exception ex) {
                response.setHeader("error", ex.getMessage());
                response.setStatus(UNAUTHORIZED.value());
                Map<String, String> error = Map.of("error-message", ex.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Boolean isRequiredAuthorization(HttpServletRequest request){
        return !isFrontResource(request) && isNecessaryAuthorization(request);
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     */
    private void setUpSpringAuthentication(String token, HttpServletRequest request) {
        if (StringUtils.hasText(token)) {
            var email = jwt.createUserByToken(token);
            var user = (CustomUserDetails)userDetailsService.loadUserByUsername(email);
            var authUser = new UsernamePasswordAuthenticationToken(user, null, null);
            authUser.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authUser);
        } else {
            throw new ResourceNotFound("Token is missing");
        }
    }

    private boolean isFrontResource(HttpServletRequest request) {
        return  request.getRequestURI().contains(".css")  ||
                request.getRequestURI().contains(".js")   ||
                request.getRequestURI().contains(".html") ||
                request.getRequestURI().contains(".png")  ||
                request.getRequestURI().contains(".ico")  ||
                request.getRequestURI().contains(".gif")  ||
                request.getRequestURI().contains(".jpg")  ||
                request.getRequestURI().contains(".ttf")  ||
                request.getRequestURI().contains(".svg");
    }

    private boolean isNecessaryAuthorization(HttpServletRequest request) {
        return  !request.getServletPath().equals("/login") &&
                !request.getServletPath().equals("/api/auth/accounts/log-in") &&
                !request.getServletPath().equals("/api/auth/accounts") &&
                !request.getServletPath().equals("/api/auth/refresh") &&
                !request.getServletPath().equals("/");
    }

}
