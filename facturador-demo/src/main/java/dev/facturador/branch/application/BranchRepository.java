package dev.facturador.branch.application;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Page<Branch> findByTraderOwnerTraderId(Long idTrader, Pageable pageable);

    List<BranchSummaryProjection> findByTraderOwnerTraderId(Long traderId);

    Boolean existsByBranchId(Long branchId);

    Boolean existsByTraderOwnerTraderId(Long traderId);
}
