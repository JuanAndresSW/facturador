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

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilterForLogin extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private CustomJWT jwt;

    public AuthenticationFilterForLogin(AuthenticationManager authenticationManager, CustomJWT jwt) {
        this.authenticationManager = authenticationManager;
        this.jwt = jwt;
    }

    /**
     * Verifica que las credenciales sean válidas <br/>
     * Y en caso de que sean válidas crea un usuario autenticado con estas
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
     * Este método es llamado cuando las credenciales pasadas a {@code attemptAuthentication} son válidas <br/>
     * Este método se encarga de crear los tokens con el usuario autenticado recibido
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
