package dev.facturador.pointofsale.application.handlers;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.pointofsale.application.PointOfSaleRepository;
import dev.facturador.pointofsale.domain.querys.PointOfSaleGetQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Slf4j
@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GetPointOfSaleQueryHandler implements PortQueryHandler<HashMap<String, Object>, PointOfSaleGetQuery> {
    @Autowired
    private final PointOfSaleRepository repository;

    @Override
    public HashMap<String, Object> handle(PointOfSaleGetQuery query) throws Exception {
        if (!repository.existsByPointOfSaleId(query.getPointOfSaleID())) {
            throw new Exception("No se ha encontrado el punto de vena");
        }
        log.info("Pase el exists");
        var pointOfSale = repository.getById(query.getPointOfSaleID());
        var response = new HashMap<String, Object>() {
            {
                put("pointOfSaleNumber", pointOfSale.getPointOfSaleNumber());
                put("fantasyName", pointOfSale.getBranchOwner().getFantasyName());
                put("street", pointOfSale.getBranchOwner().getStreet());
                put("addressNumber", pointOfSale.getBranchOwner().getAddressNumber());
                put("phone", pointOfSale.getBranchOwner().getPhone());
                put("cuit", pointOfSale.getBranchOwner().getTraderOwner().getCuit());
                put("vatCategory", pointOfSale.getBranchOwner().getTraderOwner().getVatCategory().vatToLowercaseAndSpanish());
            }
        };

        return response;
    }
}
