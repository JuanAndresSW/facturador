package dev.facturador.pointofsale.application.command;

import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import dev.facturador.pointofsale.application.usecase.DeletePointOfSaleUseCase;
import org.springframework.stereotype.Component;

/**Manejador del comando {@link PointOfSaleDeleteCommand}*/
@Component
public class PointOfSaleDeleteCommandHandler
        implements CommandHandler<PointOfSaleDeleteCommand> {

    private final DeletePointOfSaleUseCase useCase;

    public PointOfSaleDeleteCommandHandler(DeletePointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(PointOfSaleDeleteCommand command) throws ResourceNotFound {
        //Verifica que el punto de venta exista para eliminarlo
        if (!useCase.verify(command.getPointOfSaleId())) {
            throw new ResourceNotFound("Esta punto de venta no existe");
        }
        //Si existe lo borra
        this.useCase.handlerPointOfSaleDelete(command.getPointOfSaleId());
    }
}
