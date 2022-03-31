package dev.facturador.mainaccount.domain.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Getter
public class UserOrTraderIsNull extends Exception {
    private String error;
    private LocalDateTime time;

    public UserOrTraderIsNull(String error) {
        this.error = error;
        this.time = LocalDateTime.now();
    }
}
