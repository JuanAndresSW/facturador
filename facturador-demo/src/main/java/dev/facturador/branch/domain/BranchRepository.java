package dev.facturador.branch.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

   Page<Branch> findByTraderOwnerIdTrader(Long idTrader, Pageable pageable);
   Boolean existsByBranchId(Long branchId);
}
