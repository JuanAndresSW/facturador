package dev.facturador.account.application;

import dev.facturador.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    //Busca una cuenta por el email o el username
    Optional<Account> findByOwnerUserUsernameOrOwnerUserEmail(String username, String email);

    //Busca una cuenta por el username
    Optional<Account> findByOwnerUserUsername(String username);

    //Elimina una cuenta por el username
    void deleteByOwnerUserUsername(String username);

    //Comprueba si ya existe una cuenta con este username
    Boolean existsByOwnerUserUsername(String username);
    //Comprueba si ya existe una cuenta con este email
    Boolean existsByOwnerUserEmail(String email);
    //Comprueba si ya existe una cuenta con este cuit
    Boolean existsByOwnerTraderCuit(String cuit);
}
