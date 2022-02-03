package dev.facturador.repository;

import dev.facturador.entities.Comerciante;
import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz para llaar al repositorio de Comerciante
 */
public interface IComercianteRepository extends CrudRepository<Comerciante, Long> {
}
