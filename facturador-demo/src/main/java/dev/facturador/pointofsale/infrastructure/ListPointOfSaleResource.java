package dev.facturador.pointofsale.infrastructure;

import dev.facturador.pointofsale.application.query.PointOfSaleListQuery;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleBranchID;
import dev.facturador.shared.application.querys.QueryBus;
import dev.facturador.shared.domain.sharedpayload.Page;
import dev.facturador.shared.domain.sharedpayload.PagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class ListPointOfSaleResource {
    private QueryBus queryBus;

    public ListPointOfSaleResource(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @GetMapping("/branch/{IDBranch}")
    public HttpEntity<PagedResponse<PointOfSale>> toListPointOfSale(
            @RequestParam(value = "index") int index,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @PathVariable(name = "IDBranch") long branchID) throws Exception {
        log.info("Entre al metodo");
        var query = PointOfSaleListQuery.Builder.getInstance()
                .page(Page.starter(index, size, sort, order))
                .branchID(PointOfSaleBranchID.starter(branchID))
                .build();

        var response = queryBus.handle(query);

        return ResponseEntity.ok().body(response);
    }
}
