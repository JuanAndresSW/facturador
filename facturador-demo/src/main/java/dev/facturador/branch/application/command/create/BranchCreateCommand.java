package dev.facturador.branch.application.command.create;

import dev.facturador.branch.domain.BranchCreate;
import dev.facturador.global.application.commands.Command;
import lombok.Getter;

/**Comando para crear la sucursal*/
@Getter
public class BranchCreateCommand extends Command {
    private final BranchCreate branchCreate;

    public BranchCreateCommand(BranchCreate branchCreate) {
        this.branchCreate = branchCreate;
    }
    /**Builder del comando*/
    public static class Builder {
        private BranchCreate branchCreate;

        public static BranchCreateCommand.Builder getInstance() {
            return new BranchCreateCommand.Builder();
        }

        public BranchCreateCommand.Builder branchCreate(BranchCreate branchCreate) {
            this.branchCreate = branchCreate;
            return this;
        }

        public BranchCreateCommand build() {
            return new BranchCreateCommand(branchCreate);
        }
    }
}
