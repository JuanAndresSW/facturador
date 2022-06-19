package dev.facturador.branch.application.command.create;

import dev.facturador.branch.application.usecase.CreateBranchUseCase;
import dev.facturador.global.application.commands.CommandHandler;
import org.springframework.stereotype.Component;

/**Manejador de comando de {@link BranchCreateCommand}*/
@Component
public class BranchCreateCommandHandler implements CommandHandler<BranchCreateCommand> {
    private final CreateBranchUseCase useCase;

    public BranchCreateCommandHandler(CreateBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchCreateCommand command) throws Exception {

        useCase.handleBranchCreate(command.getBranchCreate());
    }
}
