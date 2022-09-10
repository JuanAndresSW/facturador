package dev.facturador.account.domain.commands;

import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.AccountUpdateRestModel;
import dev.facturador.global.domain.abstractcomponents.commands.Command;
import lombok.Builder;
import lombok.Data;

/**
 * Comando para actualizar la cuenta
 */
@Data
@Builder
public class AccountUpdateCommand extends Command {
    private final AccountUpdateRestModel accountUpdateRestModel;
    private final Account actualAccount;

}
