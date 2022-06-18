package dev.facturador.pointofsale.application.command;

import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.pointofsale.application.usecase.CreatePointOfSaleUseCase;
import org.springframework.stereotype.Component;

@Component
public class PointOfSaleCreateCommandHandler implements CommandHandler<PointOfSaleCreateCommand> {

    private final CreatePointOfSaleUseCase useCase;

    public PointOfSaleCreateCommandHandler(CreatePointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(PointOfSaleCreateCommand command) throws Exception {

        useCase.handlerPointOfSaleCreation(command.getPointOfSaleCreate());
    }
}
