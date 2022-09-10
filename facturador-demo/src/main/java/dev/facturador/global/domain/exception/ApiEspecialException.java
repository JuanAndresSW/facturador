package dev.facturador.global.domain.exception;

import java.util.ArrayList;
import java.util.List;

public final class ApiEspecialException extends Exception {
    private final String message;
    private Integer code;
    private List<ApiErrorField> errors;

    public ApiEspecialException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public ApiEspecialException(String message) {
        super(message);
        this.message = message;
    }

    public void addError(ApiErrorField error) {
        this.errors.add(error);
    }

}
