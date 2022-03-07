package dev.facturador.mainaccount.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface IMainAccountRepository extends JpaRepository<MainAccount, Long> {

    Optional<MainAccount> findByUserMainAccountUsernameOrUserMainAccountEmail(String username, String email);

    Boolean existsByUserMainAccountUsername(String username);

    Boolean existsByUserMainAccountEmail(String email);

    Boolean existsByAccountOwnerUniqueKey(String uniqueKey);
}
