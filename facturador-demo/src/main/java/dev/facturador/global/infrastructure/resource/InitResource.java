package dev.facturador.global.infrastructure.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.facturador.global.domain.CustomUserDetails;
import dev.facturador.global.infrastructure.adapters.CustomJWT;
import dev.facturador.global.infrastructure.springservice.CustomUserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**EndPoint para verificar si el Token no ha expirado*/
@RestController
@RequestMapping(path = "/api/auth")
public class InitResource {
    private final CustomJWT jwt;
    private final CustomUserDetailsService service;

    public InitResource(CustomUserDetailsService service, CustomJWT jwt) {
        this.service = service;
        this.jwt = jwt;
    }

    /**
     * Se encarga de que el Token no ha expirado
     * Si es válido envía los datos para realizar operaciones en la API
     * (Util cuando el usuario se va y no cierra sesion. En estos casos solo se guarda el token)
     *
     *
     * @param request {@link HttpServletRequest} de la API de HttpServlet. Maneja la request
     * @param response {@link HttpServletResponse} de la API HttpServlet. Marca la respuesta de la request
     * @throws IOException
     */
    @GetMapping("/init")
    public void initApp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = this.verifyTokenIsTrue(request, response);
        var initResponse = this.createResponseWithEmail(email);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getOutputStream(), initResponse);
    }

    /**
     * Verifica que el token sea valido
     * @return Devuelve el email guardado dentro del token para buscar al usuario
     * */
    public String verifyTokenIsTrue(HttpServletRequest request, HttpServletResponse response) throws IOException {
       //Saca el token del header
        String authToken = request.getHeader(AUTHORIZATION);
        try{
            //Comprueba si es valido
            if (jwt.verifyToken(authToken)) {
                //Recupera el Email del token
                return jwt.createUserByToken(authToken);
            }
        } catch (Exception ex) {
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error-message", ex.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        throw new RuntimeException("Token is missing");
    }

    /**
     * Crea la respuesta para el inicio de la aplicacion
     *
     * @param email Email para buscar el usuario
     * @return Retorna los valores en forma de mapa
     */
    private LinkedHashMap<String, String> createResponseWithEmail(String email) throws IOException {
        //Busca al usuario con el email
        var user = ((CustomUserDetails) service.loadUserByUsername(email));

        return new LinkedHashMap<String, String>(
                        Map.of("username", user.getUsername(),
                                "IDTrader", String.valueOf(user.getTraderId()),
                                "actives", String.valueOf(user.getActives()),
                                "passives", String.valueOf(user.getPassives()) ));
    }
}
