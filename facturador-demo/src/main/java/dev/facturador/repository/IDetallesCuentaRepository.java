package dev.facturador.repository;

import dev.facturador.entities.DetallesCuenta;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interfaz para llaar al repositorio de Detalles de Cuenta
 */
public interface IDetallesCuentaRepository extends JpaRepository<DetallesCuenta, Long> {
    //Para que busque por un username
    DetallesCuenta findByUsername(String username);
}
