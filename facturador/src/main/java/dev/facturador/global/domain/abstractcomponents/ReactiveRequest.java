package dev.facturador.global.domain.abstractcomponents;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;

@Component
public interface ReactiveRequest<V extends Class, T> {

    ResponseEntity<String> makePostRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body);

    ResponseEntity<String> makePostRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body, MediaType type);

    ResponseEntity<T> makeRequest(
            String method,
            String uri,
            BodyInserter<?, ? super ClientHttpRequest> body,
            MediaType type,
            V typeClass,
            String headerName,
            String headerValue);

    ResponseEntity<T> makeRequestNotHeader(
            String method,
            String uri,
            BodyInserter<?, ? super ClientHttpRequest> body,
            MediaType type,
            V typeClass);
}
