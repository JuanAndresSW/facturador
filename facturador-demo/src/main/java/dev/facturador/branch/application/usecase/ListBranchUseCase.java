package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchRepository;
import dev.facturador.branch.domain.BranchTraderId;
import dev.facturador.shared.domain.sharedpayload.Page;
import dev.facturador.shared.domain.sharedpayload.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class ListBranchUseCase {

    @Autowired
    private BranchRepository repository;

    public PagedResponse<Branch> handle(BranchTraderId branchTraderId, Page page) {
        Pageable pageable = null;
        if (page.getOrder().equals("asc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.ASC, page.getSort());
        }
        if (page.getOrder().equals("desc")) {
            pageable = PageRequest.of(page.getIndex(), page.getSize(), Sort.Direction.DESC, page.getSort());
        }

        var pages = repository.findByTraderOwnerIdTrader(branchTraderId.IDTrader(), pageable);
        List<Branch> content = pages.getNumberOfElements() == 0 ? Collections.emptyList() : pages.getContent();
        if (!content.isEmpty()) {
            content.forEach(branch -> branch.setLogo(null));
        }
        return new PagedResponse<Branch>(content, pages.getNumber(), pages.getSize(),
                pages.getTotalElements(), pages.getTotalPages(), pages.isLast());
    }
}
