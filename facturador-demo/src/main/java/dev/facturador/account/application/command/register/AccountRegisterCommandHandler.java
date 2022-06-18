package dev.facturador.account.application.command.register;

import dev.facturador.account.application.usecases.ChecksAccountUseCase;
import dev.facturador.account.application.usecases.RegisterAccountUseCase;
import dev.facturador.account.domain.exception.IndexesAreRepeated;
import dev.facturador.global.application.commands.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class AccountRegisterCommandHandler implements CommandHandler<AccountRegisterCommand> {
    private final RegisterAccountUseCase useCase;
    private final ChecksAccountUseCase checkUseCase;

    public AccountRegisterCommandHandler(RegisterAccountUseCase useCase, ChecksAccountUseCase checkUseCase) {
        this.useCase = useCase;
        this.checkUseCase = checkUseCase;
    }

    @Override
    public void handle(AccountRegisterCommand command) throws Exception {
        String message = checkUseCase.errorIfAnyIndexIsInUse(
                command.getAccountRegister().userRegister().username(),
                command.getAccountRegister().userRegister().email(),
                command.getAccountRegister().traderRegister().cuit());

        if (StringUtils.hasText(message)) {
            throw new IndexesAreRepeated(message);
        }
        useCase.handleAccountRegister(command.getAccountRegister());
    }
}
