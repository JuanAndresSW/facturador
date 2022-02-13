package dev.facturador.jwt;


import dev.facturador.services.impl.CustomUserDetailsService;
import dev.facturador.dto.security.CustomUserDetails;
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

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Metodo llamado para comprobar que el token sea valido
     * @param request Recupera la request con la Api de HttpServlet
     * @param response Necesario para recibir marcar el filtro
     * @param filterChain Envia que ha sucedido en el filtro
     * @throws ServletException Excepcion que puede arrojar sendError
     * @throws IOException Excepcion que puede arrojar sendError
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
        String token = this.getJWTOfTheRequest(request);
        //Entra si el token es valido
        if(StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
            //obtenemos el username del token
            String username = jwtProvider.getValue(token);

            //Comprueba si autentico el username
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
            var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        }catch (Exception e){
            log.error("Fail, el metodo doFilter" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    /**
     *  Recupera el Token recibido en la Request
     * @param request Recupera la request con la Api HttpServlet
     * @return
     */
    private String getJWTOfTheRequest(HttpServletRequest request) {
        //Recupera el Token almacenado en el Header
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.replace("Bearer", "");
        }
        return null;
    }
}
