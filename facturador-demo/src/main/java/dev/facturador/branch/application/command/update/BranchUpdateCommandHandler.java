package dev.facturador.branch.application.command.update;

import dev.facturador.branch.application.usecase.UpdateBranchUseCase;
import dev.facturador.global.application.commands.CommandHandler;
import org.springframework.stereotype.Component;

/**Manejador del comando {@link BranchUpdateCommand}*/
@Component
public class BranchUpdateCommandHandler implements CommandHandler<BranchUpdateCommand> {
    private final UpdateBranchUseCase useCase;

    public BranchUpdateCommandHandler(UpdateBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchUpdateCommand command) throws Exception {
        //Sede al caso de uso la actualización
        useCase.handleBranchUpdate(command.getBranchUpdate(), command.getBranch());
    }
}
