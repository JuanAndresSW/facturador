package dev.facturador.branch.application.handlers;

import dev.facturador.branch.application.BranchRepository;
import dev.facturador.branch.domain.query.BranchesSummaryQuery;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class SummaryBranchQueryHandler
        implements PortQueryHandler<LinkedList<HashMap<String, Object>>, BranchesSummaryQuery> {
    @Autowired
    private final BranchRepository repository;

    @Override
    public LinkedList<HashMap<String, Object>> handle(BranchesSummaryQuery query) throws Exception {
        if (!this.repository.existsByTraderOwnerTraderId(query.getTraderId())) {
            throw new Exception("No tienes sucursales");
        }

        var projection = repository.findByTraderOwnerTraderId(query.getTraderId());
        var response = new LinkedList<HashMap<String, Object>>();

        projection.forEach((x) -> {
            response.addLast(new HashMap<String, Object>() {
                {
                    put("branchID", x.getBranchId());
                    put("fantasyName", x.getFantasyName());
                    put("city", x.getCity());
                    put("street", x.getStreet());
                    put("addressNumber", x.getAddressNumber());
                    put("pointsOfSale", x.getPointsOfSale());
                }
            });
        });

        return response;
    }

}
