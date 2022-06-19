package dev.facturador.account.infrastructure;

import dev.facturador.global.domain.CustomUserDetails;
import dev.facturador.global.infrastructure.adapters.CustomJWT;
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

/** Esta clase hace de filtro para la autenticacion */
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilterForLogin extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private CustomJWT jwt;

    public AuthenticationFilterForLogin(AuthenticationManager authenticationManager, CustomJWT jwt) {
        log.info("authFilterFL se ejecuta");
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
    }

    /**
     * Verifica que las credenciales sean "auténticas" <br/>
     * Y en caso de que sean válidas crea un usuario autenticado por Spring
     *
     * @param request  Objeto {@link HttpServletRequest} recibe la request
     * @param response Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     * @return {@link Authentication} Usuario autenticado
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        log.info("attemptAuth se ejecuta");
        String usernameOrEmail = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");
        var authenticationToken = new UsernamePasswordAuthenticationToken(usernameOrEmail, password);
        return this.authenticationManager.authenticate(authenticationToken);
    }

    /**
     * Este método es llamado cuando las credenciales pasadas a {@code attemptAuthentication} son autenticas <br/>
     * Aqui es donde se crean los tokens y se pasa la informacion necesaria para log-in
     *
     * @param request    Objeto {@link HttpServletRequest} recibe la request
     * @param response   Objeto {@link HttpServletResponse} marca la respuesta de la {@code request}
     * @param chain      Objeto {@link FilterChain} En caso de que quieras llevar la cadena de filtro a otro
     * @param authResult Objeto {@link Authentication} Este es el usuario salido de {@code attemptAuthentication}
     */
    @Override
    protected void successfulAuthentication
    (HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        log.info("succAuth se ejecuta");
        var user = (CustomUserDetails) authResult.getPrincipal();
        var URL = request.getRequestURI();
        String accesToken = jwt.createAccesToken(user.getEmail(), URL);
        String refreshToken = jwt.createRefreshToken(user.getEmail(), URL);

        response.setHeader("accessToken", accesToken);
        response.setHeader("refreshToken", refreshToken);

        response.setHeader("username", user.getUsername());
        response.setHeader("actives", String.valueOf(user.getActives()));
        response.setHeader("passives", String.valueOf(user.getPassives()));
        response.setHeader("IDTrader", String.valueOf(user.getTraderId()));

    }

}
