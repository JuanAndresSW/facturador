package dev.facturador.branch.application.command.delete;

import dev.facturador.branch.application.usecase.DeleteBranchUseCase;
import dev.facturador.branch.domain.exception.BranchNotFound;
import dev.facturador.shared.application.comandbus.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BranchDeleteCommandHandler implements CommandHandler<BranchDeleteCommand> {

    private DeleteBranchUseCase useCase;

    public BranchDeleteCommandHandler(DeleteBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchDeleteCommand command) throws BranchNotFound {
        if(!useCase.verify(command.getBranchID())){
            throw new BranchNotFound("Esta sucursal no existe", HttpStatus.NO_CONTENT, false);
        }
        useCase.handle(command.getBranchID());
    }
}
