package dev.facturador.jwt;


import dev.facturador.dto.security.UsernamePasswordWithTimeoutAuthenticationToken;
import dev.facturador.services.impl.CustomUserDetailsService;
import dev.facturador.dto.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Value("${security.jwt.header}")
    private String HEADER;
    @Value("${security.jwt.prefix}")
    private String PREFIX;

    /**
     * Metodo llamado para comprobar que el token sea valido
     * @param request Recupera la request con la Api de HttpServlet
     * @param response Necesario para recibir marcar el filtro
     * @param filterChain Envia que ha sucedido en el filtro
     * @throws IOException Excepcion que puede arrojar sendError
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        try{
            var token = this.getJWTOfTheRequest(request);
            if(!jwtProvider.validateToken(token)) {
                SecurityContextHolder.clearContext();
            }
            if(jwtProvider.validateToken(token)) {
                var auth = this.setUpSpringAuthentication(jwtProvider.getValue(token));
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }

    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     * @param username Recibe el username para cargar el Usuario y su rol
     * @return Retorna un usuario autenticado
     */
    private UsernamePasswordWithTimeoutAuthenticationToken setUpSpringAuthentication(String username) {
        var userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordWithTimeoutAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    /**
     *  Recupera el Bearer Token recibido en la Header de la request
     * @param request Recupera la request con la Api HttpServlet
     * @return Si esta bien retorna el Token sin Bearer
     */
    private String getJWTOfTheRequest(HttpServletRequest request) {
        //Recupera el Token almacenado en el Header
        var bearerToken = request.getHeader(HEADER);
        if(this.tokenExists(bearerToken)) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }

    /**
     * Logica para verificacion dle otken
     * @param token token ah comprobar
     */
    private boolean tokenExists(String token) {
        return StringUtils.hasText(token) && token.startsWith(PREFIX);
    }
}
