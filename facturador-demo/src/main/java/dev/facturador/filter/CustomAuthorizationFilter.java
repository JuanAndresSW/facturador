package dev.facturador.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
            filterChain.doFilter(request, response);
        }
        if (isRequiredAuthorization(request)) {
            String authHeader = request.getHeader(AUTHORIZATION);
            if (jwt.verifyAuthToken(authHeader)) {
                try {
                    String token = authHeader.substring("Bearer ".length());
                    var authUser = createUserAuthorizedWithToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authUser);
                    filterChain.doFilter(request, response);
                } catch (Exception ex) {
                    var error = propagatingErrorMessage(response, ex);
                    response = (HttpServletResponse) error.get(1);
                    new ObjectMapper().writeValue(response.getOutputStream(), error.get(0));
                }

            } else {
                filterChain.doFilter(request, response);
            }

        }
    }

    private static List<Object> propagatingErrorMessage(HttpServletResponse response, Exception ex) {
        log.error("Error logging in: {}", ex.getMessage());
        response.setHeader("error", ex.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error-message", ex.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);

        List<Object> respuesta = new ArrayList<>();
        respuesta.add(error);
        respuesta.add(response);
        return respuesta;
    }

    private UsernamePasswordAuthenticationToken createUserAuthorizedWithToken(String token) {
        DecodedJWT decodedJWT = jwt.createDecoder(token);
        String username = jwt.getSubject(decodedJWT);
        String rol = jwt.getClaimRol(decodedJWT);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(rol));

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public static boolean isNotRequiredAuthorization(HttpServletRequest request) {
        return request.getServletPath().equals("/login") || request.getServletPath().equals("/api/auth/login") || request.getServletPath().equals("/api/auth/mainaccounts");
    }

    public static boolean isRequiredAuthorization(HttpServletRequest request) {
        return !request.getServletPath().equals("/login") && !request.getServletPath().equals("/api/auth/login") && !request.getServletPath().equals("/api/auth/mainaccounts");
    }

}
