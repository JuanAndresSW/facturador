package dev.facturador.global.domain.exception;

public final class ApiException extends Exception {
    private Integer code;
    private String message;
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
