package dev.facturador.branch.domain.command;

import dev.facturador.branch.domain.BranchCreateRestModel;
import dev.facturador.global.domain.abstractcomponents.command.Command;
import lombok.Builder;
import lombok.Data;

/**
 * Comando para crear la sucursal
 */
@Data
@Builder
public class BranchCreateCommand extends Command {
    private final BranchCreateRestModel branchCreateRestModel;
}
