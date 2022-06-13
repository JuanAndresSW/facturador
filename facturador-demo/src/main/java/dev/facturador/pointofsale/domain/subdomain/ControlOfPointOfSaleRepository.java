package dev.facturador.pointofsale.domain.subdomain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ControlOfPointOfSaleRepository extends JpaRepository<ControlOfPointOfSale, Long> {

    Optional<ControlOfPointOfSale> findByTraderIdTrader(Long traderID);
}
