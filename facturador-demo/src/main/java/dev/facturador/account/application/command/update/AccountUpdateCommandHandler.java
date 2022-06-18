package dev.facturador.account.application.command.update;

import dev.facturador.account.application.usecases.UpdateAccountUseCase;
import dev.facturador.account.domain.exception.ErrorInDataForUpdate;
import dev.facturador.global.application.commands.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AccountUpdateCommandHandler implements CommandHandler<AccountUpdateCommand> {

    private final UpdateAccountUseCase useCase;

    public AccountUpdateCommandHandler(UpdateAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(AccountUpdateCommand command) throws ErrorInDataForUpdate {
        var result = useCase.allChecksToUpdate(command.getMainAccountUpdate(), command.getMainAccount());
        if (StringUtils.hasText(result)) {
            throw new ErrorInDataForUpdate(result);
        }

        useCase.handleAccountUpdate(command.getMainAccountUpdate(), command.getMainAccount());
    }
}
