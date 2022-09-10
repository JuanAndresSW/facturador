package dev.facturador.account.domain.commands;

import dev.facturador.global.domain.abstractcomponents.commands.Command;
import lombok.Builder;
import lombok.Data;

/**
 * Comando para eliminar la cuenta
 */
@Data
@Builder
public class AccountDeleteCommand extends Command {
    private final String username;

}
