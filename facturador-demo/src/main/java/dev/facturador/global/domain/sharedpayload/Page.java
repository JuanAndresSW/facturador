package dev.facturador.global.domain.sharedpayload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase para almacenar los parámetros de la Paginación
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public final class Page {
    private Integer index;
    private Integer size;
    private String sort;
    private String order;

    public static Page starter(Integer index, Integer size, String sort, String order) {
        return new Page(index, size, sort, order);
    }
}
