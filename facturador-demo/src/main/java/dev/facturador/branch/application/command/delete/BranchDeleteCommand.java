package dev.facturador.branch.application.command.delete;

import dev.facturador.branch.domain.BranchID;
import dev.facturador.shared.application.commands.Command;

public class BranchDeleteCommand extends Command {
    private BranchID branchID;

    public BranchDeleteCommand(BranchID branchID) {
        this.branchID = branchID;
    }

    public BranchID getBranchID() {
        return this.branchID;
    }

    public static class Builder {
        private BranchID branchID;

        public static BranchDeleteCommand.Builder getInstance() {
            return new BranchDeleteCommand.Builder();
        }

        public BranchDeleteCommand.Builder branchID(BranchID branchID) {
            this.branchID = branchID;
            return this;
        }

        public BranchDeleteCommand build() {
            return new BranchDeleteCommand(branchID);
        }
    }
}
