package dev.facturador.repository;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.CuentaSecundaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interfaz para llaar al repositorio de Cuenta Secudnaria
 */
public interface ICuentaSecundariaRepository extends JpaRepository<CuentaSecundaria, Long> {

    /**
     * Query Custom no proporcionada por la clase CrudRepository
     */
    @Query(value = "SELECT sa FROM CuentaSecundaria  sa WHERE sa.secondaryAccountDetails.idAccountDetails = :id")
    CuentaSecundaria findByIdByDetailsSecondaryAccount(@Param("id") long id);

}
