package dev.facturador.trader.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.trader.domain.TraderBranchCollection;
import dev.facturador.trader.domain.TraderID;
import dev.facturador.trader.domain.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetBranchesUseCase {

    @Autowired
    private TraderRepository repository;

    public TraderBranchCollection handle(TraderID traderID){
        var trader =repository.getById(traderID.IDTrader());
        return TraderBranchCollection.starter((List<Branch>) trader.getBranchOutlets());
    }
}
