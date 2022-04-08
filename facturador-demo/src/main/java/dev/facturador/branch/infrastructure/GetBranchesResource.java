package dev.facturador.branch.infrastructure;

import dev.facturador.shared.application.querybus.QueryBus;
import dev.facturador.trader.application.query.TraderGetBranchesQuery;
import dev.facturador.trader.domain.TraderID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping(path = "/api/branches")
public final class GetBranchesResource {
    private QueryBus queryBus;

    public GetBranchesResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @GetMapping("/{IDTrader}")
    public HttpEntity create(@PathVariable @NotEmpty Long IDTrader) throws Exception {

        var query = TraderGetBranchesQuery.Builder.getInstance()
                .traderID(TraderID.valueOf(IDTrader)).build();

        var traderBranch= queryBus.handle(query);
        if(true){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.ok().build();
        }

    }
}
