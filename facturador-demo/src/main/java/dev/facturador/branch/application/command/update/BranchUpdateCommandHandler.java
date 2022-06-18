package dev.facturador.branch.application.command.update;

import dev.facturador.branch.application.usecase.UpdateBranchUseCase;
import dev.facturador.global.application.commands.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class BranchUpdateCommandHandler implements CommandHandler<BranchUpdateCommand> {
    private final UpdateBranchUseCase useCase;

    public BranchUpdateCommandHandler(UpdateBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchUpdateCommand command) throws Exception {


        useCase.handleBranchUpdate(command.getBranchUpdate(), command.getBranch());
    }
}
