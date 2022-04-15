package dev.facturador.mainaccount.domain.exception;

import lombok.ToString;

@ToString
public class MainAccountNotExists extends Exception {

    public MainAccountNotExists(String message) {
        super(message);
    }
}
