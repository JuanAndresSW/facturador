package dev.facturador.branch.domain.command;

import dev.facturador.global.domain.abstractcomponents.command.Command;
import lombok.Builder;
import lombok.Data;

/**
 * Comando para eliminar la sucursal
 */
@Data
@Builder
public class BranchDeleteCommand extends Command {
    private final Long branchId;
}
