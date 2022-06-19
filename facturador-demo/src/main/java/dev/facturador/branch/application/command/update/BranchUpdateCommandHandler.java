package dev.facturador.branch.application.command.update;

import dev.facturador.branch.application.usecase.UpdateBranchUseCase;
import dev.facturador.shared.application.commands.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class BranchUpdateCommandHandler implements CommandHandler<BranchUpdateCommand> {
    private UpdateBranchUseCase useCase;

    public BranchUpdateCommandHandler(UpdateBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchUpdateCommand command) throws Exception {


        useCase.handle(command.getBranchUpdate(), command.getBranch());
    }
}
