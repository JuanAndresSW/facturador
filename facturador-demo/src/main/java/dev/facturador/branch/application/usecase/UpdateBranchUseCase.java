package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchRepository;
import dev.facturador.branch.domain.BranchUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**Caso de uso para actualizar la sucursal*/
@Service
@Transactional
public class UpdateBranchUseCase {
    @Autowired
    private BranchRepository repository;

    public void handleBranchUpdate(BranchUpdate updatedBranch, Branch branch) {
        var updateBranch = Branch.create(updatedBranch, branch);
        repository.saveAndFlush(updateBranch);
    }
}
