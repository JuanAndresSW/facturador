package dev.facturador.operation.core.infrastructure;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.core.domain.DocumentHistory;
import dev.facturador.operation.core.domain.ListAnyOperationQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@RestController
@RequestMapping(path = "/api/operations")
public class ListAnyOperationResource {
    private final PortQueryBus queryBus;

    @Autowired
    public ListAnyOperationResource(PortQueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/traders/{IDTrader}")
    public Mono<HttpEntity<List<DocumentHistory>>> getAnyFullOperation(
                                                                        @NotNull @PathVariable(value = "IDTrader") long traderID,
                                                                        @RequestParam(value = "IDBranch", required = false) String branchId,
                                                                        @RequestParam(value = "pointOfSaleNumber",required = false) String pointOfSaleNumber,
                                                                        @RequestParam(value = "repository",required = false) String repository) throws Exception {

        final var query = ListAnyOperationQuery.builder()
                .repository(StringUtils.hasText(repository) ? repository : "default")
                .traderID(traderID)
                .branchId(branchId != null ? Long.parseLong(branchId) : 0L)
                .pointOfSaleNumber(pointOfSaleNumber != null ? Long.parseLong(pointOfSaleNumber) : 0L)
                .build();

        var response = queryBus.handle(query);

        return Mono.just(response).map(r -> ok().body(r));
    }
}
