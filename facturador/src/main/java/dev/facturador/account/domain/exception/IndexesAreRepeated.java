package dev.facturador.account.domain.exception;

import lombok.ToString;

@ToString
public class IndexesAreRepeated extends Exception {

    public IndexesAreRepeated(String message) {
        super(message);
    }
}
