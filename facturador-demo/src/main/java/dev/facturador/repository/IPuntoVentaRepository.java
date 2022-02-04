package dev.facturador.repository;

import dev.facturador.entities.PuntoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz para llaar al repositorio de Punto de Venta
 */
public interface IPuntoVentaRepository extends JpaRepository<PuntoVenta, Long> {
}
