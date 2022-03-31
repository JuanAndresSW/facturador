package dev.facturador.shared.infrastructure.config;

import dev.facturador.shared.domain.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }


    @Component
    @Slf4j
    public static class JWTEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
                throws IOException, ServletException {
            log.error("Responding with unauthorized error. Message - {}", e.getMessage());
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry, You're not authorized to access this resource.");
        }
    }

    /**
     * Clase para manejar las excepciones
     */
    @RestControllerAdvice
    @Slf4j
    public static class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        /**
         * Maneja la excepcion de tipo ConstraintViolationException
         */
        @ExceptionHandler(ConstraintViolationException.class)
        public HttpEntity<ErrorResponse> handler(ConstraintViolationException ex) {
            var errorDetalles = new ErrorResponse(new Date(), ex.getMessage(), String.valueOf(ex.getCause()));
            return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
        }

        /**
         * Maneja una excepcion generica <br/>
         * Es decir cualquier excepcion que no tenga un metodo propio se dirigira a este metodo
         *
         * @param exception  Recibe la excepcion
         * @param webRequest Intercepta la request
         * @return Retorna el dto ErrorDetails y envia codigo 400
         */
        @ExceptionHandler(Exception.class)
        public HttpEntity<ErrorResponse> hanfleGenericException(Exception exception, WebRequest webRequest) {
            var errorDetalles = new ErrorResponse(new Date(), exception.getMessage(), exception.getLocalizedMessage());
            return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
        }

        /**
         * Maneja la excepcion de tipo Runtime exception
         *
         * @param exception  Recibe la excepcion
         * @param webRequest Intercepta la request
         * @return Retorna el dto ErrorDetails y envia codigo 404
         */
        @ExceptionHandler(RuntimeException.class)
        public HttpEntity<ErrorResponse> handleRuntimeExceotion(RuntimeException exception, WebRequest webRequest) {
            var errorDetalles = new ErrorResponse(new Date(), exception.getMessage(), exception.getLocalizedMessage());
            return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
        }


        /**
         * Sobre escribe el metodo que maneja la  excepcion MethodArgumentNotValidException
         *
         * @param ex      Recupera la excepcion MethodArgumentNotValidException
         * @param headers Indica que recibe el header de la request
         * @param status  Indica que recibe el estado actual
         * @param request Intercepta la request con WebRequest
         * @return Retorna la respuesta y el estado de 400
         */
        @Override
        public ResponseEntity<Object> handleMethodArgumentNotValid
        (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            Map<String, String> errores = new HashMap<>();

            ex.getBindingResult().getAllErrors().forEach(error -> {
                String name = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errores.put(name, message);
            });
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }
    }
}
