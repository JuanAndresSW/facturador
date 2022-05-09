package dev.facturador.branch.infrastructure;

import dev.facturador.branch.domain.Branch;
import dev.facturador.shared.domain.sharedpayload.Page;
import dev.facturador.shared.application.querybus.QueryBus;
import dev.facturador.branch.application.query.tolist.BranchListQuery;
import dev.facturador.branch.domain.BranchTraderId;
import dev.facturador.shared.domain.sharedpayload.PagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/branches")
public class ListBrancheResource {
    private QueryBus queryBus;

    public ListBrancheResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @GetMapping("/trader/{IDTrader}")
    public HttpEntity<PagedResponse<Branch>> toListBranch(
            @RequestParam(value = "index") int index,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @PathVariable(name = "IDTrader") long IDTrader) throws Exception {

        var query = BranchListQuery.Builder.getInstance()
                .traderID(BranchTraderId.valueOf(IDTrader))
                .page(Page.starter(index, size, sort, order))
                .build();

        var response = queryBus.handle(query);

        return ResponseEntity.ok().body(response);
    }
}
