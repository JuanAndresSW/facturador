package dev.facturador.pointofsale.domain.subdomain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointsOfSaleControlRepository extends JpaRepository<PointsOfSaleControl, Long> {

    Optional<PointsOfSaleControl> findByTraderTraderId(Long traderID);
}
