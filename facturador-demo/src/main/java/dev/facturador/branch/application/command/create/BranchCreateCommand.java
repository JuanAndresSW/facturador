package dev.facturador.branch.application.command.create;

import dev.facturador.branch.domain.BranchCreate;
import dev.facturador.global.application.commands.Command;

public class BranchCreateCommand extends Command {
    private final BranchCreate branchCreate;

    public BranchCreateCommand(BranchCreate branchCreate) {
        this.branchCreate = branchCreate;
    }

    public BranchCreate getPointOfSaleCreate() {
        return this.branchCreate;
    }

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
