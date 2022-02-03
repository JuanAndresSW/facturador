package dev.facturador.repository;

import dev.facturador.entities.CuentaPrincipal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz para llaar al repositorio de Cuenta principal
 */
public interface ICuentaPrincipalRepository extends CrudRepository<CuentaPrincipal, Long> {

    /**
     * Query Custom no proporcionada por CruRepository
     */
    @Query(value = "SELECT am FROM CuentaPrincipal am WHERE am.mainAccountDetails.idAccountDetails = :id")
    CuentaPrincipal findByIdByDetailsMainAccount(@Param("id") long id);
}
