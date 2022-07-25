package dev.facturador.account.application.command.handlers;

import dev.facturador.account.application.command.AccountUpdateCommand;
import dev.facturador.account.application.usecases.UpdateAccountUseCase;
import dev.facturador.account.domain.exception.ErrorInDataForUpdate;
import dev.facturador.global.application.commands.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**Manejador del comando {@link AccountUpdateCommand}*/
@Component
public class AccountUpdateCommandHandler implements CommandHandler<AccountUpdateCommand> {
    private final UpdateAccountUseCase useCase;

    public AccountUpdateCommandHandler(UpdateAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(AccountUpdateCommand command) throws ErrorInDataForUpdate {
        //Comprueba que la informacion nueva no sea exactamente la misma, y que la contraseña sea correcta
        var result = useCase.allChecksToUpdate(command.getAccountUpdate(), command.getActualAccount());
        if (StringUtils.hasText(result)) {
            throw new ErrorInDataForUpdate(result);
        }
        //Actualiza si no tiene problemas en la comprobación
        useCase.handleAccountUpdate(command.getAccountUpdate(), command.getActualAccount());
    }
}
