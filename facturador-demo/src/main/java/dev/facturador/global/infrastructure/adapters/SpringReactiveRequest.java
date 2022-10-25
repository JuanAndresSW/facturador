package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.domain.abstractcomponents.ReactiveRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Clase para realizar request con SpringWebFlux
 */
@Service
public class SpringReactiveRequest<V extends Class, T>
        implements ReactiveRequest<V, T> {

    private final WebClient client;

    public SpringReactiveRequest() {
        client = WebClient.builder().baseUrl("http://localhost:8080").build();
    }

    /**
     * Realiza una request HTTP POST a la ruta que se le indique dentro de localhost:8080
     */
    @Override
    public ResponseEntity<String> makePostRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body) {
        return client.post().uri(uri).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL).body(body)
                .retrieve().toEntity(String.class).block();
    }

    /**
     * Realiza una request HTTP POST a la ruta que se le indique dentro de localhost:8080
     **/
    @Override
    public ResponseEntity<String> makePostRequest(String uri, BodyInserter<?, ? super ClientHttpRequest> body, MediaType type) {
        return client.post().uri(uri).contentType(type)
                .accept(MediaType.ALL).body(body)
                .retrieve().toEntity(String.class).block();
    }

    /**
     * Realiza una request HTTP de cualquier método a la ruta que se le indique dentro de localhost:8080
     **/
    @Override
    public ResponseEntity<T> makeRequest(
            String method,
            String uri,
            BodyInserter<?, ? super ClientHttpRequest> body,
            MediaType type,
            V typeClass,
            String headerName,
            String headerValue) {

        switch (method) {
            case "POST":
                return (ResponseEntity<T>) client.post().uri(uri).contentType(type)
                        .accept(MediaType.ALL).body(body).header(headerName, headerValue)
                        .retrieve().toEntity(typeClass).block();
            case "GET":
                return (ResponseEntity<T>) client.get().uri(uri).accept(type).header(headerName, headerValue)
                        .retrieve().toEntity(typeClass).block();
            case "PUT":
                return (ResponseEntity<T>) client.put().uri(uri).contentType(type)
                        .accept(MediaType.ALL).body(body)
                        .retrieve().toEntity(typeClass).block();
            case "DELETE":
                return (ResponseEntity<T>) client.delete().uri(uri).retrieve().toEntity(typeClass).block();
            default:
                return null;
        }
    }

    @Override
    public ResponseEntity<T> makeRequestNotHeader(
            String method,
            String uri,
            BodyInserter<?, ? super ClientHttpRequest> body,
            MediaType type,
            V typeClass) {
        switch (method) {
            case "POST":
                return (ResponseEntity<T>) client.post().uri(uri).contentType(type)
                        .accept(MediaType.ALL).body(body)
                        .retrieve().toEntity(typeClass).block();
            case "GET":
                return (ResponseEntity<T>) client.get().uri(uri).accept(type)
                        .retrieve().toEntity(typeClass).block();
            case "PUT":
                return (ResponseEntity<T>) client.put().uri(uri).contentType(type)
                        .accept(MediaType.ALL).body(body)
                        .retrieve().toEntity(typeClass).block();
            case "DELETE":
                return (ResponseEntity<T>) client.delete().uri(uri).retrieve().toEntity(typeClass).block();
            default:
                return null;
        }
    }


}
