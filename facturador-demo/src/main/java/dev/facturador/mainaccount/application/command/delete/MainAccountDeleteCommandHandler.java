package dev.facturador.mainaccount.application.command.delete;

import dev.facturador.mainaccount.application.usecases.DeleteMainAccountUseCase;
import dev.facturador.mainaccount.domain.exception.MainAccountNotExists;
import dev.facturador.shared.application.commands.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class MainAccountDeleteCommandHandler implements CommandHandler<MainAccountDeleteCommand> {
    private DeleteMainAccountUseCase useCase;

    public MainAccountDeleteCommandHandler(DeleteMainAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(MainAccountDeleteCommand command) throws Exception {
        if (!useCase.existsByUsername(command.getMainAccountIdUsername().getUsername())) {
            throw new MainAccountNotExists("No existe una cuenta con este username");
        }

        useCase.deleteByUsername(command.getMainAccountIdUsername().getUsername());
    }
}
