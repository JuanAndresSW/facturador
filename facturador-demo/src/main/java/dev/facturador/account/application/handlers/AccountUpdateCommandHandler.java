package dev.facturador.account.application.handlers;

import dev.facturador.account.application.AccountRepository;
import dev.facturador.account.application.UpdateAccountService;
import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.commands.AccountUpdateCommand;
import dev.facturador.account.domain.exception.ErrorInDataForUpdate;
import dev.facturador.global.domain.abstractcomponents.commands.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Manejador del comando {@link AccountUpdateCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class AccountUpdateCommandHandler implements CommandHandler<AccountUpdateCommand> {
    private final UpdateAccountService useCase;
    @Autowired
    private final AccountRepository repository;

    /**
     * Maneja las actualizaciones a las cuentas de usuarios
     *
     * @param command Contiene los datos para actualizar una cuenta de usuario
     * @throws ErrorInDataForUpdate
     */
    @Override
    public void handle(AccountUpdateCommand command) throws ErrorInDataForUpdate {
        //Comprueba que la informacion nueva no sea exactamente la misma, y que la contraseña sea correcta
        var result = useCase.allChecksToUpdate(command.getAccountUpdateRestModel(), command.getActualAccount());
        if (StringUtils.hasText(result)) {
            throw new ErrorInDataForUpdate(result);
        }

        repository.saveAndFlush(Account.create(command.getAccountUpdateRestModel(), command.getActualAccount()));
    }
}
