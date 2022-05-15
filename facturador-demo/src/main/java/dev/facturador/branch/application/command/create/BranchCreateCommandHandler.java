package dev.facturador.branch.application.command.create;

import dev.facturador.branch.application.usecase.CreateBranchUseCase;
import dev.facturador.shared.application.comandbus.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
public class BranchCreateCommandHandler implements CommandHandler<BranchCreateCommand> {
    private CreateBranchUseCase useCase;

    public BranchCreateCommandHandler(CreateBranchUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(BranchCreateCommand command) throws Exception {

        useCase.handle(command.getPointOfSaleCreate());
    }
}
