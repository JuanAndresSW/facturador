package dev.facturador.branchaccount.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface IBranchAccountRepository extends JpaRepository<CuentaSecundaria, Long> {
    @Query(value = "FROM CuentaSecundaria sa WHERE sa.userBranchAccount.username = :username")
    Optional<CuentaSecundaria> findByUsername(@Param("username") String username);

    Optional<CuentaSecundaria> findByUserBranchAccountUsernameOrUserBranchAccountEmail(String username, String email);

}
