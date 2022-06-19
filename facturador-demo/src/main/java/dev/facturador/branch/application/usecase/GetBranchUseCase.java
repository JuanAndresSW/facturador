package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchID;
import dev.facturador.branch.domain.BranchRepository;
import dev.facturador.shared.domain.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBranchUseCase {
    @Autowired
    private BranchRepository repository;

    public Branch get(BranchID branchID) throws ResourceNotFound {
        var branch = repository.findById(branchID.getBranchID());
        if (branch.isEmpty()) {
            throw new ResourceNotFound("No existe esta sucursal");
        }
        return branch.get();
    }
}
