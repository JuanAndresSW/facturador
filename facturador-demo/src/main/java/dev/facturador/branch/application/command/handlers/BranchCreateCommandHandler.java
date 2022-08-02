package dev.facturador.branch.application.command.handlers;

import dev.facturador.branch.application.command.BranchCreateCommand;
import dev.facturador.branch.application.usecase.CreateBranchUseCase;
import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.trader.application.usecase.TraderWildCardUseCase;
import org.springframework.stereotype.Component;

/**Manejador de comando de {@link BranchCreateCommand}*/
@Component
public class BranchCreateCommandHandler implements CommandHandler<BranchCreateCommand> {
    private final CreateBranchUseCase useCase;
    private final TraderWildCardUseCase traderWildCardUseCase;

    public BranchCreateCommandHandler(CreateBranchUseCase useCase, TraderWildCardUseCase traderWildCardUseCase) {
        this.useCase = useCase;
        this.traderWildCardUseCase = traderWildCardUseCase;
    }

    @Override
    public void handle(BranchCreateCommand command) throws Exception {
        if(!this.traderWildCardUseCase.handleExistsTrader(command.getBranchCreate().getIDTrader())){
            throw new Exception("No existe este comerciante, por lo tanto no puede crear una sucursal");
        }
        useCase.handleCreateBranch(command.getBranchCreate());
    }
}
