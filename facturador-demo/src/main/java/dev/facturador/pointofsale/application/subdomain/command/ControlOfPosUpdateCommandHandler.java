package dev.facturador.pointofsale.application.subdomain.command;

import dev.facturador.pointofsale.application.subdomain.usecase.UpdateControlOfPosUseCase;
import dev.facturador.shared.application.commands.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class ControlOfPosUpdateCommandHandler implements CommandHandler<ControlOfPosUpdateCommand> {

    private UpdateControlOfPosUseCase useCase;

    public ControlOfPosUpdateCommandHandler(UpdateControlOfPosUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(ControlOfPosUpdateCommand command)
            throws Exception {

        this.useCase.handle(command.getData());
    }
}
