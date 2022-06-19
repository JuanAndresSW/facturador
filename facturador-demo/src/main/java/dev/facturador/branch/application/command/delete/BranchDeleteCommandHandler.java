package dev.facturador.branch.application.command.delete;

import dev.facturador.branch.application.usecase.DeleteBranchUseCase;
import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.stereotype.Component;

/**Manejador del comando {@link BranchDeleteCommand}*/
@Component
public class BranchDeleteCommandHandler implements CommandHandler<BranchDeleteCommand> {

    private final DeleteBranchUseCase useCase;

    public BranchDeleteCommandHandler(DeleteBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchDeleteCommand command) throws ResourceNotFound {
        //Verifica que exista esta sucursal
        if (!useCase.verifyExistsBranchByBranchId(command.getBranchId())) {
            throw new ResourceNotFound("Esta sucursal no existe");
        }
        //Si existe le sede la eliminacion al caso de uso
        useCase.handleBranchDelete(command.getBranchId());
    }
}
