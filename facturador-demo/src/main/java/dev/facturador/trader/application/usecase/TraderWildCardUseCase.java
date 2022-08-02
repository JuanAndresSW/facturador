package dev.facturador.trader.application.usecase;

import dev.facturador.trader.domain.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class TraderWildCardUseCase {

    @Autowired
    private TraderRepository repository;

    public Boolean handleExistsTrader(Long traderId){
        return Boolean.TRUE.equals(repository.existsByTraderId(traderId));
    }
}
