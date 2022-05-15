package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchID;
import dev.facturador.branch.domain.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class DeleteBranchUseCase {

    @Autowired
    private BranchRepository repository;

    public void handle(BranchID value) {
        repository.delete(Branch.create(value));
    }

    public boolean verify(BranchID value){
        return repository.existsByBranchId(value.getBranchID());
    }
}
