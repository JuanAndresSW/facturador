package dev.facturador.branch.application.command.delete;

import dev.facturador.branch.application.usecase.DeleteBranchUseCase;
import dev.facturador.shared.application.commands.CommandHandler;
import dev.facturador.shared.domain.exception.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BranchDeleteCommandHandler implements CommandHandler<BranchDeleteCommand> {

    private DeleteBranchUseCase useCase;

    public BranchDeleteCommandHandler(DeleteBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchDeleteCommand command) throws ResourceNotFound {
        if (!useCase.verify(command.getBranchID())) {
            throw new ResourceNotFound("Esta sucursal no existe");
        }
        useCase.handle(command.getBranchID());
    }
}
