package dev.facturador.operation.wholeoperation.infrastructure;

import dev.facturador.global.domain.abstractcomponents.querys.QueryBus;
import dev.facturador.operation.wholeoperation.domain.model.WholeOperationDisplayed;
import dev.facturador.operation.wholeoperation.domain.querys.GetAnyWholeOperationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/api/wholes/operations")
public class GetAnWholeOperationResource {
    private final QueryBus queryBus;

    @Autowired
    public GetAnWholeOperationResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/repositories/{repository}")
    public HttpEntity<WholeOperationDisplayed> getAnyWholeOperation(@NotEmpty @RequestParam(value = "operationNumber") String operationNumber,
                                                                    @NotEmpty @RequestParam(value = "type") String type,
                                                                    @NotNull @RequestParam(value = "IDTrader") long traderID,
                                                                    @NotNull @PathVariable(value = "repository") String repository) throws Exception {

        var query = GetAnyWholeOperationQuery.builder()
                .operationNumber(operationNumber)
                .type(type)
                .traderId(traderID)
                .repository(repository).build();

        var response = queryBus.handle(query);

        return ResponseEntity.ok(response);
    }
}
