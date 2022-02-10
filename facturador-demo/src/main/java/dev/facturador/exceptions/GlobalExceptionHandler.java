package dev.facturador.exceptions;

import dev.facturador.dto.ErrorDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.*;
import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Clase para manejar las excepciones
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja Excepciones de ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetailsDto> handler(ConstraintViolationException ex){
        ErrorDetailsDto errorDetalles = new ErrorDetailsDto(new Date(), ex.getMessage(), String.valueOf(ex.getCause()));
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejua una Excepcion generica
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> manejarGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetailsDto errorDetalles = new ErrorDetailsDto(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

    /**
     * Metodo Override maneja MethodArgumentNotValidException
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String name = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errores.put(name, message);
        });
        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }
}
