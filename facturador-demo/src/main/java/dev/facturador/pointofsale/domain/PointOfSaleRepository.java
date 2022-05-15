package dev.facturador.pointofsale.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Long> {

    Page<PointOfSale> findByBranchOwnerBranchId(Long branchID, Pageable pageable);
}
