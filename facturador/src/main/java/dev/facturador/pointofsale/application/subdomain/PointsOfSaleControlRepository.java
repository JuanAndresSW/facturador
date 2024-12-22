package dev.facturador.pointofsale.application.subdomain;


import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointsOfSaleControlRepository extends JpaRepository<PointsOfSaleControl, Long> {

    Optional<PointsOfSaleControl> findByTraderTraderId(Long traderID);
}
