package dev.facturador.repository;

import dev.facturador.entities.Comerciante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz para llaar al repositorio de Comerciante
 */
public interface IComercianteRepository extends JpaRepository<Comerciante, Long> {
}
