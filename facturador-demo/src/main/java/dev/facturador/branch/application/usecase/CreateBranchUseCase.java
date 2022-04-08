package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchCreate;
import dev.facturador.branch.domain.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class CreateBranchUseCase {

    @Autowired
    private BranchRepository repository;

    public void handle(BranchCreate value) {
        var branch = Branch.create(value);
        repository.save(branch);
    }

}
