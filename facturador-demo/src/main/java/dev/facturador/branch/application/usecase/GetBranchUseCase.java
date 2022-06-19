package dev.facturador.branch.application.usecase;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchRepository;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Caso de uso para recuperar una sucursal*/
@Service
public class GetBranchUseCase {
    @Autowired
    private BranchRepository repository;

    public Branch handleGetBranch(Long branchID) throws ResourceNotFound {
        //Busca la sucursal
        var branch = repository.findById(branchID);
        //Verifica que el objeto no este vacio
        if (branch.isEmpty()) {
            throw new ResourceNotFound("No existe esta sucursal");
        }
        //Si hay sucursal la retorna
        return branch.get();
    }
}
