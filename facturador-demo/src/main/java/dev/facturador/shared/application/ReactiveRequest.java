package dev.facturador.shared.application;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

public interface ReactiveRequest {

    ResponseEntity<String> initRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body);

    ResponseEntity<String> initRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body, MediaType type);
}
