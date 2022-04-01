package dev.facturador.mainaccount.domain.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class IndexesAreRepeated extends Exception {

    public IndexesAreRepeated(String message){
        super(message);
    }
}
