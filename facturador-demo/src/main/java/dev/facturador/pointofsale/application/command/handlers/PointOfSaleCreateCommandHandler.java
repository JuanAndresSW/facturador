package dev.facturador.pointofsale.application.command.handlers;

import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.pointofsale.application.command.PointOfSaleCreateCommand;
import dev.facturador.pointofsale.application.usecase.CreatePointOfSaleUseCase;
import org.springframework.stereotype.Component;
/**Manejador del comando {@link PointOfSaleCreateCommand}*/
@Component
public class PointOfSaleCreateCommandHandler implements CommandHandler<PointOfSaleCreateCommand> {

    private final CreatePointOfSaleUseCase useCase;

    public PointOfSaleCreateCommandHandler(CreatePointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    /**Sede la operacion al caso de uso*/
    @Override
    public void handle(PointOfSaleCreateCommand command) throws Exception {

        useCase.handlerPointOfSaleCreation(command.getPointOfSaleCreate());
    }
}
