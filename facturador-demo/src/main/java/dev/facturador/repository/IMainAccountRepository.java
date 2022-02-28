package dev.facturador.repository;

import dev.facturador.entities.CuentaPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface IMainAccountRepository extends JpaRepository<CuentaPrincipal, Long> {
    @Query(value = "FROM CuentaPrincipal am WHERE am.userMainAccount.username = :username")
    Optional<CuentaPrincipal> findByUsername(@Param("username") String username);

    Optional<CuentaPrincipal> findByUserMainAccountUsernameOrUserMainAccountEmail(String username, String email);

    Boolean existsByUserMainAccountUsername(String username);

    Boolean existsByUserMainAccountEmail(String email);

    Boolean existsByAccountOwnerUniqueKey(String uniqueKey);
}
