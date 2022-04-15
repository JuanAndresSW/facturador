package dev.facturador.branch.application.command;

import dev.facturador.branch.domain.BranchCreate;
import dev.facturador.shared.application.comandbus.Command;

public class BranchCreateCommand extends Command {
    private BranchCreate branchCreate;

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

        public BranchCreateCommand.Builder pointOfSaleCreate(BranchCreate branchCreate) {
            this.branchCreate = branchCreate;
            return this;
        }

        public BranchCreateCommand build() {
            return new BranchCreateCommand(branchCreate);
        }
    }
}
