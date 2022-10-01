package dev.facturador.security.domain;

public class SaveNotFoundException extends RuntimeException {
    public SaveNotFoundException(String id) {
        super("Post #" + id + " was not found.");
    }
}
