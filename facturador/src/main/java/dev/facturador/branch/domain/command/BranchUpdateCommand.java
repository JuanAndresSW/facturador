package dev.facturador.branch.domain.command;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchUpdateRestModel;
import dev.facturador.global.domain.abstractcomponents.command.Command;
import lombok.Builder;
import lombok.Data;

/**
 * Comando para actualizar sucursal
 */
@Data
@Builder
public class BranchUpdateCommand extends Command {
    private final BranchUpdateRestModel branchUpdateRestModel;
    private final Branch branch;
}
