package dev.facturador.trader.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class TraderWildCardService {

    @Autowired
    private TraderRepository repository;

    public Boolean handleExistsTrader(Long traderId) {
        return Boolean.TRUE.equals(repository.existsByTraderId(traderId));
    }
}
