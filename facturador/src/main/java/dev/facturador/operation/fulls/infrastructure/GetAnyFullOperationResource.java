package dev.facturador.operation.fulls.infrastructure;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.fulls.domain.model.FullOperationDisplayed;
import dev.facturador.operation.fulls.domain.querys.GetAnyFullOperationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api/operations/fulls",produces = "application/json")
public class GetAnyFullOperationResource {
    private final PortQueryBus queryBus;

    @Autowired
    public GetAnyFullOperationResource(PortQueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{repository}")
    public Mono<HttpEntity<FullOperationDisplayed>> getAnyFullOperation(@NotNull @RequestParam(value = "operationId") long operationId,
                                                                   @NotNull @PathVariable(value = "repository") String repository) throws Exception {
       final var query = GetAnyFullOperationQuery.builder()
                .operationId(operationId)
                .repository(repository).build();

        var response = queryBus.handle(query);

        return Mono.just(response).map(r -> ok().body(r));
    }
}
