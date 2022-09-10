package dev.facturador.branch.application.handlers;

import dev.facturador.branch.application.BranchRepository;
import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.command.BranchUpdateCommand;
import dev.facturador.global.domain.abstractcomponents.commands.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manejador del comando {@link BranchUpdateCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class BranchUpdateCommandHandler implements CommandHandler<BranchUpdateCommand> {
    @Autowired
    private final BranchRepository repository;

    /**
     * Maneja las actualizaciones de las sucursales
     *
     * @param command Contiene los datos para actualizar una sucursal
     * @throws Exception
     */
    @Override
    public void handle(BranchUpdateCommand command) throws Exception {
        var updatedBranch = Branch.create(command.getBranchUpdateRestModel(), command.getBranch());
        repository.saveAndFlush(updatedBranch);
    }
}
