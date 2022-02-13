package dev.facturador.repository;

import dev.facturador.entities.PuntoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPuntoVentaRepository extends JpaRepository<PuntoVenta, Long> {
}
