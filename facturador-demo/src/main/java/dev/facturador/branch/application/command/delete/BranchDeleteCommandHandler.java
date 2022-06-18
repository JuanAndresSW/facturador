package dev.facturador.branch.application.command.delete;

import dev.facturador.branch.application.usecase.DeleteBranchUseCase;
import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class BranchDeleteCommandHandler implements CommandHandler<BranchDeleteCommand> {

    private final DeleteBranchUseCase useCase;

    public BranchDeleteCommandHandler(DeleteBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchDeleteCommand command) throws ResourceNotFound {
        if (!useCase.verify(command.getBranchId())) {
            throw new ResourceNotFound("Esta sucursal no existe");
        }
        useCase.handleBranchDelete(command.getBranchId());
    }
}
