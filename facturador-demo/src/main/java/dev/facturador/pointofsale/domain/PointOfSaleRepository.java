package dev.facturador.pointofsale.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Long> {
    Boolean existsByUserMainAccountUsername(String username);
}
