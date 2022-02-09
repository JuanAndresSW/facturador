package dev.facturador.repository;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.CuentaSecundaria;
import dev.facturador.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Interfaz para llaar al repositorio de Detalles de Cuenta
 */
public interface IUserRepository extends JpaRepository<Usuarios, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Usuarios> findByUsername(String username);
    Optional<Usuarios> findByUsernameOrEmail(String username,String email);
    Usuarios findByEmail(String email);

    @Query(value = "SELECT ma FROM Usuarios u JOIN CuentaPrincipal ma ON u.userId = ma.userMainAccount.userId WHERE u.username = :username")
    CuentaPrincipal usernamePertainToMainAccount(@Param("username") String username);

    @Query(value = "SELECT sa FROM Usuarios u JOIN CuentaSecundaria sa ON u.userId = sa.userSecondaryAccount.userId WHERE u.username = :username")
    CuentaSecundaria usernamePertainToSecondaryAccount(@Param("username") String username);
}
