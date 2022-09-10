package dev.facturador.account.domain.commands;

import dev.facturador.account.domain.AccountRegisterRestModel;
import dev.facturador.global.domain.abstractcomponents.command.Command;
import lombok.Builder;
import lombok.Data;

/**
 * Comando para registrar la cuenta
 */
@Data
@Builder
public class AccountRegisterCommand extends Command {
    private final AccountRegisterRestModel accountRegisterRestModel;

}
