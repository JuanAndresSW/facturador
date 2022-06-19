package dev.facturador.branch.domain.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
@Getter
public class BranchNotFound extends Exception {

    private String message;
    @JsonIgnore
    private HttpStatus status;
    private Boolean success;

    public BranchNotFound(String message, HttpStatus status, Boolean success) {
        super();
        this.message = message;
        this.status = status;
        this.success = success;
    }

    public BranchNotFound(String message) {
        super(message);
    }
}
