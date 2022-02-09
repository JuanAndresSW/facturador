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

//Controlador para excepciones
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Indico que excepcion controla
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<String> handler(ConstraintViolationException ex){
        List<String> message = new LinkedList<>();
        if(ex != null){
            var exs = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            violations.forEach(x -> message.add(x.getMessage()));
        }
        return message;
    }

    /**
     * Manejua una Excepcion generica
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> manejarGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetailsDto errorDetalles = new ErrorDetailsDto(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }

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
