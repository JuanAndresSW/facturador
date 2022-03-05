package dev.facturador.branchaccount.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface IBranchAccountRepository extends JpaRepository<BranchAccount, Long> {
    @Query(value = "FROM BranchAccount sa WHERE sa.userBranchAccount.username = :username")
    Optional<BranchAccount> findByUsername(@Param("username") String username);

    Optional<BranchAccount> findByUserBranchAccountUsernameOrUserBranchAccountEmail(String username, String email);

}
