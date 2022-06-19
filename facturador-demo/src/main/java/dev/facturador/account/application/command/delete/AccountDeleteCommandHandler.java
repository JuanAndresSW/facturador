package dev.facturador.account.application.command.delete;

import dev.facturador.account.application.usecases.ChecksAccountUseCase;
import dev.facturador.account.application.usecases.DeleteAccountUseCase;
import dev.facturador.global.application.commands.CommandHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.stereotype.Component;

/**Manjador del comando de eliminar {@link AccountDeleteCommand}*/
@Component
public class AccountDeleteCommandHandler implements CommandHandler<AccountDeleteCommand> {
    private final DeleteAccountUseCase useCase;
    private final ChecksAccountUseCase checkUseCase;

    public AccountDeleteCommandHandler(DeleteAccountUseCase useCase, ChecksAccountUseCase checkUseCase) {
        this.useCase = useCase;
        this.checkUseCase = checkUseCase;
    }

    @Override
    public void handle(AccountDeleteCommand command) throws Exception {
        //Comprueba que exista esta cuenta antes de eliminar
        if (!checkUseCase.checkAccountExistsByUsername(command.getUsername())) {
            throw new ResourceNotFound("No existe una cuenta con este username");
        }

        useCase.handleAccountDelete(command.getUsername());
    }
}
