package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchCreate;
import dev.facturador.branch.domain.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**Caso de uso para crear un sucursal*/
@Service
@Transactional
public class CreateBranchUseCase {

    @Autowired
    private BranchRepository repository;

    public void handleCreateBranch(BranchCreate value) {
        repository.save(Branch.create(value));
    }
}
