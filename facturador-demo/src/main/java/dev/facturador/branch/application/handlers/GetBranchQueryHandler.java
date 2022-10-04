package dev.facturador.branch.application.handlers;

import dev.facturador.branch.application.BranchRepository;
import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.query.BranchGetQuery;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.security.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Manejador de la Query devuelve la entidad {@link Branch}
 */
@AllArgsConstructor
@Service
public class GetBranchQueryHandler implements PortQueryHandler<Branch, BranchGetQuery> {
    @Autowired
    private final BranchRepository repository;

    @Override
    public Branch handle(BranchGetQuery query) throws Exception {
        var branch = repository.findById(query.getBranchId());

        if (branch.isEmpty()) {
            throw new ResourceNotFound("No existe esta sucursal");
        }
        return branch.get();
    }
}
