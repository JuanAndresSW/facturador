package dev.facturador.account.application.handlers;

import dev.facturador.account.application.AccountRepository;
import dev.facturador.account.application.ChecksAccountService;
import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.commands.AccountRegisterCommand;
import dev.facturador.account.domain.exception.IndexesAreRepeated;
import dev.facturador.global.domain.ClockProvider;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Manejador del comando {@link AccountRegisterCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class RegisterAccountCommandHandler implements PortCommandHandler<AccountRegisterCommand> {
    private final ChecksAccountService checkUseCase;
    @Autowired
    private final AccountRepository repository;
    @Autowired
    private final ClockProvider clock;

    /**
     * Maneja el comando de registrar una cuenta de usuario
     */
    @Override
    public void handle(AccountRegisterCommand command) throws Exception {
        //Verifica que la información que debe de ser única ya no esté registrada
        String message = checkUseCase.errorIfAnyIndexIsInUse(
                command.getAccountRegisterRestModel().userRegister().username(),
                command.getAccountRegisterRestModel().userRegister().email(),
                command.getAccountRegisterRestModel().traderRegister().cuit());

        if (StringUtils.hasText(message)) {
            throw new IndexesAreRepeated(message);
        }

        repository.save(Account.toEntity(command.getAccountRegisterRestModel(), clock));
    }
}
