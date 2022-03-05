package dev.facturador.pointofsale.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPointOfSaleRepository extends JpaRepository<PointOfSale, Long> {
}
