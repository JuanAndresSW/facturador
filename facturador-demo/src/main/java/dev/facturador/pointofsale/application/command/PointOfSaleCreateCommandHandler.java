package dev.facturador.pointofsale.application.command;

import dev.facturador.pointofsale.application.usecase.CreatePointOfSaleUseCase;
import dev.facturador.shared.application.commands.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class PointOfSaleCreateCommandHandler implements CommandHandler<PointOfSaleCreateCommand> {

    private CreatePointOfSaleUseCase useCase;

    public PointOfSaleCreateCommandHandler(CreatePointOfSaleUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(PointOfSaleCreateCommand command) throws Exception {

        useCase.handlePointOfSaleCreation(command.getPointOfSaleCreate());
    }
}
