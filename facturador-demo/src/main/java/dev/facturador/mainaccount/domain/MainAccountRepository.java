package dev.facturador.mainaccount.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MainAccountRepository extends JpaRepository<MainAccount, Long> {

    Optional<MainAccount> findByUserMainAccountUsernameOrUserMainAccountEmail(String username, String email);

    MainAccount findByUserMainAccountUsername(String username);

    void deleteByUserMainAccountUsername(String username);

    Boolean existsByUserMainAccountUsername(String username);
    Boolean existsByUserMainAccountEmail(String email);
    Boolean existsByAccountOwnerUniqueKey(String uniqueKey);
}
