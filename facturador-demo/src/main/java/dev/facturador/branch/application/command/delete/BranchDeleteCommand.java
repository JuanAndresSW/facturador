package dev.facturador.branch.application.command.delete;

import dev.facturador.global.application.commands.Command;

public class BranchDeleteCommand extends Command {
    private final Long branchId;

    public BranchDeleteCommand(Long branchId) {
        this.branchId = branchId;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public static class Builder {
        private Long branchId;

        public static BranchDeleteCommand.Builder getInstance() {
            return new BranchDeleteCommand.Builder();
        }

        public BranchDeleteCommand.Builder branchId(Long branchID) {
            this.branchId = branchID;
            return this;
        }

        public BranchDeleteCommand build() {
            return new BranchDeleteCommand(branchId);
        }
    }
}
