package dev.facturador.account.application.command.handlers;

import dev.facturador.account.application.command.AccountRegisterCommand;
import dev.facturador.account.application.usecases.ChecksAccountUseCase;
import dev.facturador.account.application.usecases.RegisterAccountUseCase;
import dev.facturador.account.domain.exception.IndexesAreRepeated;
import dev.facturador.global.application.commands.CommandHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/** Manejador del comando {@link AccountRegisterCommand}*/
@Component
public class AccountRegisterCommandHandler implements CommandHandler<AccountRegisterCommand> {
    private final RegisterAccountUseCase useCase;
    private final ChecksAccountUseCase checkUseCase;

    public AccountRegisterCommandHandler(RegisterAccountUseCase useCase, ChecksAccountUseCase checkUseCase) {
        this.useCase = useCase;
        this.checkUseCase = checkUseCase;
    }

    /**Maneja el comando de eliminar con los casos de uso*/
    @Override
    public void handle(AccountRegisterCommand command) throws Exception {
        //Verifica que la información que debe de ser única ya no esté registrada
        String message = checkUseCase.errorIfAnyIndexIsInUse(
                command.getAccountRegister().userRegister().username(),
                command.getAccountRegister().userRegister().email(),
                command.getAccountRegister().traderRegister().cuit());

        if (StringUtils.hasText(message)) {
            throw new IndexesAreRepeated(message);
        }
        //Registra la cuenta
        useCase.handleAccountRegister(command.getAccountRegister());
    }
}
