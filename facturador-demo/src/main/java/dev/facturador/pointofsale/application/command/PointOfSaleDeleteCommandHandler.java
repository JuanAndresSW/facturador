package dev.facturador.pointofsale.application.command;

import dev.facturador.pointofsale.application.usecase.DeletePointOfSaleUseCase;
import dev.facturador.shared.application.commands.CommandHandler;
import dev.facturador.shared.domain.exception.ResourceNotFound;
import org.springframework.stereotype.Component;

@Component
public class PointOfSaleDeleteCommandHandler
        implements CommandHandler<PointOfSaleDeleteCommand> {

    private DeletePointOfSaleUseCase useCase;

    public PointOfSaleDeleteCommandHandler(DeletePointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(PointOfSaleDeleteCommand command) throws ResourceNotFound {
        this.useCase.handlePointOfSaleDelete(command.getPointOfSaleId());
    }
}
