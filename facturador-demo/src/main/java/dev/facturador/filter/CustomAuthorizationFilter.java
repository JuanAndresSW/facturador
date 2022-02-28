package dev.facturador.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private JWTUtil jwt;

    public CustomAuthorizationFilter(JWTUtil jwt) {
        this.jwt = jwt;
    }

    /**
     * Filtro para indicar si se debe autorizar o no al recibir una request
     *
     * @param request     Parametro para agarrar el flujo de la request
     * @param response    Parametro para marcar la respuesta
     * @param filterChain envia la respuesta del filtro con este
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (isNotRequiredAuthorization(request)) {
            log.info("---ACA TENGO QUE ESTAR EN EL LOGIN--");
            filterChain.doFilter(request, response);
        }
        if (isRequiredAuthorization(request)) {
            String authHeader = request.getHeader(AUTHORIZATION);
            if(!jwt.verifyAuthToken(authHeader)){
                filterChain.doFilter(request, response);
            }
            if (jwt.verifyAuthToken(authHeader)) {
                try {
                    var authUser = jwt.createUserByToken(authHeader);
                    SecurityContextHolder.getContext().setAuthentication(authUser);
                    filterChain.doFilter(request, response);
                } catch (Exception ex) {
                    log.error("Error logging in: {}", ex.getMessage());
                    response.setHeader("error", ex.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error-message", ex.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
        }
    }

    private boolean isNotRequiredAuthorization(HttpServletRequest request) {
        return request.getServletPath().equals("/login") || request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/mainaccounts") || request.getServletPath().equals("/api/auth/refresh");
    }

    private boolean isRequiredAuthorization(HttpServletRequest request) {
        return !request.getServletPath().equals("/login") && !request.getServletPath().equals("/api/auth/login") && !request.getServletPath().equals("/api/auth/mainaccounts") && !request.getServletPath().equals("/api/auth/refresh");
    }

}
