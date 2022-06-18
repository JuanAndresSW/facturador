package dev.facturador.pointofsale.application.command;

import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import dev.facturador.pointofsale.application.usecase.DeletePointOfSaleUseCase;
import org.springframework.stereotype.Component;

@Component
public class PointOfSaleDeleteCommandHandler
        implements CommandHandler<PointOfSaleDeleteCommand> {

    private final DeletePointOfSaleUseCase useCase;

    public PointOfSaleDeleteCommandHandler(DeletePointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(PointOfSaleDeleteCommand command) throws ResourceNotFound {
        if (!useCase.verify(command.getPointOfSaleId())) {
            throw new ResourceNotFound("Esta punto de venta no existe");
        }
        this.useCase.handlerPointOfSaleDelete(command.getPointOfSaleId());
    }
}
