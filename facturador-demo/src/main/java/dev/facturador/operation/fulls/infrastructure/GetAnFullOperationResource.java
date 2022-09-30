package dev.facturador.operation.fulls.infrastructure;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.operation.fulls.domain.model.FullOperationDisplayed;
import dev.facturador.operation.fulls.domain.querys.GetAnyFullOperationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/api/wholes/operations")
public class GetAnFullOperationResource {
    private final PortQueryBus queryBus;

    @Autowired
    public GetAnFullOperationResource(PortQueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/repositories/{repository}")
    public HttpEntity<FullOperationDisplayed> getAnyFullOperation(@NotEmpty @RequestParam(value = "operationNumber") String operationNumber,
                                                                   @NotEmpty @RequestParam(value = "type") String type,
                                                                   @NotNull @RequestParam(value = "IDTrader") long traderID,
                                                                   @NotNull @RequestParam(value = "IDBranch") long branchId,
                                                                   @NotNull @PathVariable(value = "repository") String repository) throws Exception {

        var query = GetAnyFullOperationQuery.builder()
                .operationNumber(operationNumber)
                .type(type)
                .traderId(traderID)
                .branchId(branchId)
                .repository(repository).build();

        var response = queryBus.handle(query);

        return ResponseEntity.ok(response);
    }
}
