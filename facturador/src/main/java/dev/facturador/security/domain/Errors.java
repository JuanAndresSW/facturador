package dev.facturador.security.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Errors(String code, String message, List<Error> errors)
        implements Serializable {

    public Errors(List<Error> errors){
        this(null, null, new ArrayList<>(errors));
    }
    public Errors(String message, List<Error> errors){
        this(null, message, new ArrayList<>(errors));
    }
    public Errors(String code, String message){
        this(code, message, null);
    }

    public void add(String path, String code, String message){
        this.errors.add(new Error(code, message, path));
    }
}
