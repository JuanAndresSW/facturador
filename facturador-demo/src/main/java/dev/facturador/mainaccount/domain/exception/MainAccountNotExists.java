package dev.facturador.mainaccount.domain.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class MainAccountNotExists extends Exception {

    public MainAccountNotExists(String message){
        super(message);
    }
}
