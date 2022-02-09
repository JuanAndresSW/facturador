package dev.facturador.jwt;

import dev.facturador.services.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            //Puede ser nulo por eso el try
        String token = this.getJWTOfTheRequest(request);

        //Entra si el token es valido
        if(StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
            //obtenemos el username del token
            String username = jwtProvider.getValue(token);

            //Cargamos el Usuario Personalizado, segun el username
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //establecemos la seguridad
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        }catch (Exception e){
            log.error("Fail, el metodo doFilter" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    //Bearer token de acceso
    private String getJWTOfTheRequest(HttpServletRequest request) {
        //Debes agregar el "Authorization" de la request
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
