package dev.facturador.repository;

import dev.facturador.entities.PuntoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointOfSaleRepository extends JpaRepository<PuntoVenta, Long> {
}