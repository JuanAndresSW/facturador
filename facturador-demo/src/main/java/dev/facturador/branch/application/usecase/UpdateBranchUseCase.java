package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchRepository;
import dev.facturador.branch.domain.BranchUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UpdateBranchUseCase {
    @Autowired
    private BranchRepository repository;

    public void handle(BranchUpdate updatedBranch, Branch branch) {
        log.info("Entree hacer save and flush");
        var updateBranch = Branch.create(updatedBranch, branch);
        repository.saveAndFlush(updateBranch);
    }
}
