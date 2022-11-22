package dev.facturador.account.domain.exception;

import lombok.ToString;

@ToString
public class ErrorInDataForUpdate extends Exception {

    public ErrorInDataForUpdate(String message) {
        super(message);
    }
}
