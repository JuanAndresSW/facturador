package dev.facturador.shared.infrastructure.config;

import dev.facturador.branch.domain.exception.BranchNotFound;
import dev.facturador.shared.domain.exception.ErrorResponse;
import dev.facturador.shared.domain.exception.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
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

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja una excepcion generica <br/>
     * Es decir cualquier excepcion que no tenga una clase propio se dirigira a este metodo
     */
    @ExceptionHandler(Exception.class)
    public HttpEntity<ErrorResponse> handleResourceNotFoundException(Exception exception, WebRequest webRequest) {
        var errorDetalles = new ErrorResponse(exception.getMessage(), webRequest.getRemoteUser());
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja la excepcion de tipo Runtime exception
     */
    @ExceptionHandler(RuntimeException.class)
    public HttpEntity<ErrorResponse> handleRuntimeExceotion(RuntimeException exception, WebRequest webRequest) {
        var errorDetalles = new ErrorResponse(exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja una BranchNotFound Exception <br/>
     */
    @ExceptionHandler(BranchNotFound.class)
    public HttpEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFound exception) {
        var errorDetalles = new ErrorResponse(exception.getMessage());
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
