package dev.facturador.pointofsale.infrastructure;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryBus;
import dev.facturador.pointofsale.domain.querys.PointOfSaleGetQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * EndPoint para sacar un punto específico
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/pointsofsale")
public class GetPointOfSale {
    private final PortQueryBus portQueryBus;

    @Autowired
    public GetPointOfSale(PortQueryBus portQueryBus) {
        this.portQueryBus = portQueryBus;
    }

    /**
     * Devuelve un punto de venta específico
     *
     * @param pointOfSaleID ID del punto de venta que se recuperara
     * @return Devuelve datos necesarios del punto de venta
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{IDPointOfSale}")
    public HttpEntity<HashMap<String, Object>> getPointOfSale(@PathVariable(name = "IDPointOfSale") long pointOfSaleID)
            throws Exception {
        var query = PointOfSaleGetQuery.builder()
                .pointOfSaleID(pointOfSaleID).build();
        var response = portQueryBus.handle(query);

        return ResponseEntity.ok(response);

    }

}
