package dev.facturador.pointofsale.application.handlers;

import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.pointofsale.application.PointOfSaleRepository;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.querys.PointOfSaleGetQuery;
import dev.facturador.security.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GetPointOfSaleQueryHandler implements PortQueryHandler<HashMap<String, Object>, PointOfSaleGetQuery> {
    @Autowired
    private final PointOfSaleRepository repository;

    @Override
    public HashMap<String, Object> handle(PointOfSaleGetQuery query) throws Exception {
        ensureExistsPointOfSale(query.getPointOfSaleID());

        var pointOfSale = repository.getById(query.getPointOfSaleID());

        return toDisplayed(pointOfSale);
    }
    private void ensureExistsPointOfSale(Long id){
        if (Boolean.FALSE.equals( repository.existsByPointOfSaleId(id) ))
            throw new ResourceNotFound("No se ha encontrado el punto de vena");
    }

    private HashMap<String, Object> toDisplayed(PointOfSale pointOfSale){
        HashMap<String, Object> source = new HashMap();
        source.put("pointOfSaleNumber", pointOfSale.getPointOfSaleNumber());
        source.put("fantasyName", pointOfSale.getBranchOwner().getFantasyName());
        source.put("street", pointOfSale.getBranchOwner().getStreet());
        source.put("addressNumber", pointOfSale.getBranchOwner().getAddressNumber());
        source.put("phone", pointOfSale.getBranchOwner().getPhone());
        source.put("cuit", pointOfSale.getBranchOwner().getTraderOwner().getCuit());
        source.put("vatCategory", pointOfSale.getBranchOwner().getTraderOwner().getVatCategory().vatToLowercaseAndSpanish());
        return source;
    }
}
