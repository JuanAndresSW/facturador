package dev.facturador.shared.infrastructure.auth;

import dev.facturador.shared.domain.CustomUserDetails;
import dev.facturador.shared.infrastructure.CustomJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private CustomJWT jwt;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, CustomJWT jwt) {
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
    }

    /**
     * Verifica que las crdenciales sean validas <br/>
     * Y en caso que sean validas crea un usuario autenticado con estas
     *
     * @param request  Objeto {@link HttpServletRequest} recibe la request
     * @param response Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     * @return {@link Authentication} user
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String usernameOrEmail = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");
        var authenticationToken = new UsernamePasswordAuthenticationToken(usernameOrEmail, password);
        return this.authenticationManager.authenticate(authenticationToken);
    }

    /**
     * Este metodo es llamado cuando las crdenciales pasadas a {@code attemptAuthentication} son validasi <br/>
     * Este metodo se encarga de crear los tokens con el usuario autenticado recibido
     *
     * @param request    Objeto {@link HttpServletRequest} recibe la request
     * @param response   Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     * @param chain      Objeto {@link FilterChain} caso que quieres filtrar de alguna manera
     * @param authResult Objeto {@link Authentication} Este parametro contiene la respuesta de {@code attemptAuthentication}
     */
    @Override
    protected void successfulAuthentication
    (HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var user = (CustomUserDetails) authResult.getPrincipal();
        var URL = request.getRequestURI().toString();
        String accesToken = jwt.createAccesToken(user.getEmail(), user.getAuthorities(), URL);
        String refreshToken = jwt.createRefreshToken(user.getEmail(), URL);

        response.setHeader("accessToken", accesToken);
        response.setHeader("refreshToken", refreshToken);

        response.setHeader("username", user.getUsername());
        response.setHeader("role", user.getAuthorities().stream().toList().get(0).getAuthority());
        response.setHeader("active", String.valueOf(user.getActive()));
        response.setHeader("pasive", String.valueOf(user.getPassive()));
        response.setHeader("IDTrader", String.valueOf(user.getIdTrader()));
    }

}
