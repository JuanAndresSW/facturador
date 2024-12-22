package dev.facturador.account.application.handlers;

import dev.facturador.account.application.AccountRepository;
import dev.facturador.account.application.ChecksAccountService;
import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.commands.AccountDeleteCommand;
import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import dev.facturador.security.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * Maneja el comando de eliminar {@link AccountDeleteCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class DeleteAccountCommandHandler implements PortCommandHandler<AccountDeleteCommand> {
    private final ChecksAccountService checkUseCase;
    @Autowired
    private final AccountRepository repository;

    /**
     * Maneja la eliminación de una cuenta de usuario
     *
     * @param command Comando contiene los datos para eliminar una cuenta de usuario
     */
    @Override
    public void handle(AccountDeleteCommand command) throws Exception {
        var response = repository.findByOwnerUserUsername(command.getUsername())
                .flatMap(account -> {
                    //Si la cuenta es nula marco el fallo como falso
                    if(isNull(account)) return Optional.of(false);
                    repository.delete(account);
                    return Optional.of(true);
                });
        //Si el fallo ocurre arroja la excepcion
        if( Boolean.TRUE.equals(response.isEmpty()) || Boolean.FALSE.equals(response.get()) ) throw new ResourceNotFound("Esta cuenta no existe");
    }

    private Boolean isNull(Account account){
        return Boolean.TRUE.equals(account == null);
    }
}
