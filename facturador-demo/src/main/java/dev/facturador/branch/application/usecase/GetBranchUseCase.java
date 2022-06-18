package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchRepository;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBranchUseCase {
    @Autowired
    private BranchRepository repository;

    public Branch handleGetBranch(Long branchID) throws ResourceNotFound {
        var branch = repository.findById(branchID);
        if (branch.isEmpty()) {
            throw new ResourceNotFound("No existe esta sucursal");
        }
        return branch.get();
    }
}
