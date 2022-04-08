package dev.facturador.mainaccount.domain.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class ErrorInDataForUpdate extends Exception {

    public ErrorInDataForUpdate(String message) {
        super(message);
    }
}
