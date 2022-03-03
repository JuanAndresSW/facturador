package dev.facturador.shared.infrastructure.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Se llama al inicio del ciclo de vida de la clase
        //No necesito que haga nada por eso esta vacio
    }

    /**
     * Crea el filtro para CORS
     *
     * @param request         Intercepta la request
     * @param servletResponse indica que envia una response y se castea para manejarlo con la APi HttpServlet
     * @param filterChain     crea el filtro
     * @throws ServletException Puede enviar esta excepcion
     * @throws IOException      Puede enviar esta excepcion
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Location");
        filterChain.doFilter(request, response);
    }

    /**
     * Necesario para el cilo de vida de CORSFilter
     * Se ejecuta despues de dejar de utilizar la clase y nuevamente no necesito que haga nada
     */
    @Override
    public void destroy() {
        //Se llama al final del ciclo de vida de la clase
        //No necesito que haga nada
    }
}