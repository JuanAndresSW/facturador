package dev.facturador.branch.application.handlers;

import dev.facturador.branch.application.BranchRepository;
import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.command.BranchDeleteCommand;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import dev.facturador.security.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manejador del comando {@link BranchDeleteCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class BranchDeletePortCommandHandler implements PortCommandHandler<BranchDeleteCommand> {
    @Autowired
    private final BranchRepository repository;

    /**
     * Maneja la eliminacion de sucursales
     *
     * @param command Contiene los datos para eliminar una sucursal
     * @throws ResourceNotFound
     */
    @Override
    public void handle(BranchDeleteCommand command) throws ResourceNotFound {
        //Verifica que exista esta sucursal
        if (!repository.existsByBranchId(command.getBranchId())) {
            throw new ResourceNotFound("Esta sucursal no existe");
        }
        //Si existe le sede la eliminacion al caso de uso
        repository.delete(Branch.create(command.getBranchId()));
    }
}
