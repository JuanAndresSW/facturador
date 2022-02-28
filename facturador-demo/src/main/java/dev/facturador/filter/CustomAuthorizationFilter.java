package dev.facturador.filter;

import dev.facturador.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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
            log.info("WHY");
            filterChain.doFilter(request, response);
        }
        if (isRequiredAuthorization(request)) {
            log.info(":)");
            String authHeader = request.getHeader(AUTHORIZATION);
            if(!jwt.verifyAuthToken(authHeader)){
                filterChain.doFilter(request, response);
            }
            if (jwt.verifyAuthToken(authHeader)) {
                var authUser = jwt.createUserAuthenticatedByAccessToken(authHeader, response);
                SecurityContextHolder.getContext().setAuthentication(authUser);
                filterChain.doFilter(request, response);
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
