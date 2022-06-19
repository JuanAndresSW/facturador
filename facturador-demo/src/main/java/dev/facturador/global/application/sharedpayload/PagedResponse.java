package dev.facturador.global.application.sharedpayload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase para almacenar los valores del output de la paginacion
 *
 * @param <T> Este parámetro cambia según el objeto que se pagine
 */
@Data
@NoArgsConstructor
public final class PagedResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PagedResponse(List<T> content, int page, int size, long totalElements, int totalPages, boolean last) {
        setContent(content);
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getContent() {
        return content == null ? null : new ArrayList<>(content);
    }

    public void setContent(List<T> content) {
        if (content == null) {
            this.content = null;
        } else {
            this.content = Collections.unmodifiableList(content);
        }
    }

    public boolean isLast() {
        return last;
    }
}
