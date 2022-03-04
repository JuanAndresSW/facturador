package dev.facturador.trader.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ITraderRepository extends JpaRepository<Comerciante, Long> {
    Boolean existsByUniqueKey(String uniqueKey);
}
