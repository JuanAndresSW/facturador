package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.application.ReactiveRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

/**Clase para realizar request con SpringWebFlux*/
@Component
public class SpringReactiveRequest implements ReactiveRequest {

    private final WebClient client;

    public SpringReactiveRequest() {
        client = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    /**Realiza una request POST a la ruta que se le indique dentro de la API*/
    @Override
    public ResponseEntity<String> initRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body) {
        return client.post().uri(uri).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL).body(body)
                .retrieve().toEntity(String.class).block();
    }
    /**Realiza una request POST a la ruta que se le indique dentro de la API*/
    @Override
    public ResponseEntity<String> initRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body, MediaType type) {
        return client.post().uri(uri).contentType(type)
                .accept(MediaType.ALL).body(body)
                .retrieve().toEntity(String.class).block();
    }

}
