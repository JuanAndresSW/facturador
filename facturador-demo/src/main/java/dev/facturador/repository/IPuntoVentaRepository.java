package dev.facturador.repository;

import dev.facturador.entities.PuntoVenta;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz para llaar al repositorio de Punto de Venta
 */
public interface IPuntoVentaRepository extends CrudRepository<PuntoVenta, Long> {
}
