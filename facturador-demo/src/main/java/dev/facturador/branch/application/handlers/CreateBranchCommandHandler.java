package dev.facturador.branch.application.handlers;

import dev.facturador.branch.application.BranchRepository;
import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.command.BranchCreateCommand;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import dev.facturador.trader.application.TraderWildCardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manejador de comando de {@link BranchCreateCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class CreateBranchCommandHandler implements PortCommandHandler<BranchCreateCommand> {
    private final TraderWildCardService traderWildCardService;
    @Autowired
    private final BranchRepository repository;

    /**
     * Maneja la creacion de sucursales
     *
     * @param command Contiene los datos para crear una sucursal
     * @throws Exception
     */
    @Override
    public void handle(BranchCreateCommand command) throws Exception {
        if (!this.traderWildCardService.handleExistsTrader(command.getBranchCreateRestModel().getIDTrader())) {
            throw new Exception("No se ha encontrado al comerciante");
        }

        repository.save(Branch.create(command.getBranchCreateRestModel()));
    }
}
