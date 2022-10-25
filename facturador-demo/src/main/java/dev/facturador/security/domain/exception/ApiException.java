package dev.facturador.security.domain.exception;

public final class ApiException extends Exception {
    private final String message;
    private Integer code;
    private String description;

    public ApiException(Integer code, String message, String description) {
        super(message);
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

}
