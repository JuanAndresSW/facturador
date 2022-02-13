package dev.facturador.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JWTEntryPoint implements AuthenticationEntryPoint {

    /**
     * Si el Token no es valido envia un "No autorizado" Y codigo "401"
     * @param request Recupera la request con la Api de HttpServlet
     * @param response Envia la repsuesta con la Api HttpServlet
     * @param authException indica que recibe esta excepcion
     * @throws IOException Excepcion que puede arrojar sendError
     * @throws ServletException Excepcion que puede arroajar sendError
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Autorizado");
    }
}
