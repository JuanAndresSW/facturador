package dev.facturador.security.infrastructure.resource;

import dev.facturador.security.domain.Error;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase se encarga de manejar el envio de errores
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja una excepcion genérica <br/>
     * Es decir cualquier excepcion que no tenga una clase propia se dirigirá a este método
     */
    @ExceptionHandler(Exception.class)
    public HttpEntity<Error> handleResourceNotFoundException(Exception exception, WebRequest webRequest) {
        var errorDetalles = new Error(exception.getMessage());

        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja la excepcion de tipo Runtime exception
     */
    @ExceptionHandler(RuntimeException.class)
    public HttpEntity<Error> handleRuntimeExceotion(RuntimeException exception, WebRequest webRequest) {
        var errorDetalles = new Error(exception.getMessage());

        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja una ResourceNotFound Exception <br/>
     */
    @ExceptionHandler(ResourceNotFound.class)
    public HttpEntity<Error> handleResourceNotFoundException(ResourceNotFound ex) {
        var errorDetalles = new Error(ex.getMessage());
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }


    /**
     * Sobre escribe el metodo que maneja la excepcion MethodArgumentNotValidException
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errores.put(name, message);
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
