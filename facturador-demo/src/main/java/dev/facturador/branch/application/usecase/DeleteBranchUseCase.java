package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**Caso de uso para eliminar una sucursal*/
@Service
@Transactional
public class DeleteBranchUseCase {

    @Autowired
    private BranchRepository repository;

    public void handleBranchDelete(Long value) {
        //Se crea la entidad en si misma y se elimina
        repository.delete(Branch.create(value));
    }
    //Verifica si la entidad con este Id existe
    public boolean verifyExistsBranchByBranchId(Long branchId) {
        return repository.existsByBranchId(branchId);
    }
}
