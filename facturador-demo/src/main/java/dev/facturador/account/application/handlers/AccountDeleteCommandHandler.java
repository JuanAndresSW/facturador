package dev.facturador.account.application.handlers;

import dev.facturador.account.application.AccountRepository;
import dev.facturador.account.application.ChecksAccountService;
import dev.facturador.account.domain.commands.AccountDeleteCommand;
import dev.facturador.global.domain.abstractcomponents.commands.CommandHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manjador del comando de eliminar {@link AccountDeleteCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class AccountDeleteCommandHandler implements CommandHandler<AccountDeleteCommand> {
    private final ChecksAccountService checkUseCase;
    @Autowired
    private final AccountRepository repository;

    /**
     * Maneja la eliminacion de una cuenta de usuario
     *
     * @param command Comando contiene los datos para eliminar una cuenta de usuario
     * @throws Exception
     */
    @Override
    public void handle(AccountDeleteCommand command) throws Exception {
        //Comprueba que exista esta cuenta antes de eliminar
        if (!checkUseCase.checkAccountExistsByUsername(command.getUsername())) {
            throw new ResourceNotFound("No existe una cuenta con este username");
        }

        repository.deleteByOwnerUserUsername(command.getUsername());
    }
}
