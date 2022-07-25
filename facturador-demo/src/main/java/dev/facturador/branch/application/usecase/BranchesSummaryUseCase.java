package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.BranchRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Slf4j
@Service
@Transactional(propagation= Propagation.REQUIRED, readOnly=true)
public class BranchesSummaryUseCase {
    @Autowired
    private BranchRepository repository;

    public LinkedList<HashMap<String, Object>> handleGetBranchesSummary(Long traderId){
        var projection =repository.findByTraderOwnerTraderId(traderId);

        var response = new LinkedList<HashMap<String, Object>>();

       projection.forEach((x)->{
           response.addLast(new HashMap<String, Object>()
           {
               {
                   put("branchID", x.getBranchId());
                   put("fantasyName", x.getFantasyName());
                   put("locality", x.getLocality());
                   put("street", x.getStreet());
                   put("addressNumber", x.getAddressNumber());
                   put("pointsOfSale", x.getPointsOfSale());
               }
           });
       });
        return response;
    }

    public Boolean handleExists(Long traderId){
        return Boolean.TRUE.equals(repository.existsByTraderOwnerTraderId(traderId));
    }

}
