package dev.facturador.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.*;
import javax.xml.registry.InvalidRequestException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public List<String> handle(ValidationException ex){
        List<String> message = new LinkedList<>();
        if(ex instanceof ConstraintViolationException){
            var exs = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            violations.forEach(x -> message.add(x.getMessage()));
        }
        return message;
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<Object> handleInvalidRequest(RuntimeException e,
                                                       WebRequest request) {
        InvalidRequestException ire = (InvalidRequestException) e;

        List<FieldErrorResource> errorResources =
                ire.getErrors().getFieldErrors().stream().map(fieldError ->
                        new FieldErrorResource(fieldError.getObjectName(),
                                fieldError.getField(),
                                fieldError.getCode(),
                                fieldError.getDefaultMessage())
                ).collect(Collectors.toList());

        ErrorResource error = new ErrorResource("InvalidRequest",
                ire.getMessage(),
                errorResources);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, BAD_REQUEST, request);
    }
}
