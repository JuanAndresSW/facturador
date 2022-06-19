package dev.facturador.pointofsale.application.usecase;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleBranchID;
import dev.facturador.pointofsale.domain.PointOfSaleRepository;
import dev.facturador.shared.domain.sharedpayload.Page;
import dev.facturador.shared.domain.sharedpayload.PagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public final class ListPointOfSaleUseCase {
    @Autowired
    private PointOfSaleRepository repository;

    public PagedResponse<PointOfSale> handleListOfPointsOfSale(PointOfSaleBranchID pointOfSaleBranchID, Page page) {
        Pageable pageable = null;
        if (page.getOrder().equals("asc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.ASC, page.getSort());
        }
        if (page.getOrder().equals("desc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.DESC, page.getSort());
        }

        var pages = repository.findByBranchOwnerBranchId(pointOfSaleBranchID.getBranchID(), pageable);

        List<PointOfSale> content = pages.getNumberOfElements() == 0 ? Collections.emptyList() : pages.getContent();

        return new PagedResponse<PointOfSale>(content, pages.getNumber(), pages.getSize(),
                pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
    }

}
