package dev.facturador.branch.infrastructure;

import dev.facturador.branch.application.query.get.BranchGetQuery;
import dev.facturador.global.application.querys.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/branches")
public class GetLogoResource {

    private final QueryBus queryBus;

    public GetLogoResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{IDBranch}/get-logo")
    public HttpEntity<String> getLogo(@PathVariable(name = "IDBranch") long IDBranch)
            throws Exception {

        var query = BranchGetQuery.Builder.getInstance()
                .branchId(IDBranch).build();

        var branch = queryBus.handle(query);

        return ResponseEntity.ok().body(branch.getLogo());
    }

}
