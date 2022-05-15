package dev.facturador.branch.application.command.update;

import dev.facturador.branch.domain.Branch;
import dev.facturador.branch.domain.BranchUpdate;
import dev.facturador.shared.application.comandbus.Command;

public class BranchUpdateCommand extends Command {
    private BranchUpdate branchUpdate;
    private Branch branch;

    public BranchUpdateCommand(BranchUpdate branchUpdate, Branch branch) {
        this.branchUpdate = branchUpdate;
        this.branch = branch;
    }

    public BranchUpdate getBranchUpdate() {
        return this.branchUpdate;
    }
    public Branch getBranch() {
        return this.branch;
    }

    public static class Builder {
        private BranchUpdate branchUpdate;
        private Branch branch;

        public static BranchUpdateCommand.Builder getInstance() {
            return new BranchUpdateCommand.Builder();
        }

        public BranchUpdateCommand.Builder branch(Branch branch) {
            this.branch = branch;
            return this;
        }
        public BranchUpdateCommand.Builder branchUpdate(BranchUpdate branchUpdate) {
            this.branchUpdate = branchUpdate;
            return this;
        }

        public BranchUpdateCommand build() {
            return new BranchUpdateCommand(branchUpdate, branch);
        }
    }
}
