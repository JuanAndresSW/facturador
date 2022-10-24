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
        var response =repository.findByOwnerUserUsername(command.getUsername())
                .flatMap((accout) -> {
                    if(isNull(accout)) {
                        return Optional.of(false);
                    }
                    repository.delete(accout);
                    return Optional.of(true);
                });
        if(!response.get()){
            throw new ResourceNotFound("Esta cuenta no existe");
        }
    }

    private Boolean isNull(Account account){
        return Boolean.TRUE.equals(account == null);
    }
}
