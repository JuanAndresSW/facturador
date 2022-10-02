package dev.facturador.account.application;

import dev.facturador.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByOwnerUserUsernameOrOwnerUserEmail(String username, String email);

    Optional<Account> findByOwnerUserUsername(String username);

    void deleteByOwnerUserUsername(String username);

    Boolean existsByOwnerUserUsername(String username);

    Boolean existsByOwnerUserEmail(String email);

    Boolean existsByOwnerTraderCuit(String cuit);
}
