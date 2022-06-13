package dev.facturador.shared.infrastructure;

import dev.facturador.shared.application.ReactiveRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SpringReactiveRequest implements ReactiveRequest {

    private WebClient client;

    public SpringReactiveRequest() {
        client = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    @Override
    public ResponseEntity<String> initRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body) {
        return client.post().uri(uri).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL).body(body)
                .retrieve().toEntity(String.class).block();
    }

    @Override
    public ResponseEntity<String> initRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body, MediaType type) {
        return client.post().uri(uri).contentType(type)
                .accept(MediaType.ALL).body(body)
                .retrieve().toEntity(String.class).block();
    }

}
