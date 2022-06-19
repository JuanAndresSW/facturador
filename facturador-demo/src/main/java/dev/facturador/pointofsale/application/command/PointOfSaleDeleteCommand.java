package dev.facturador.pointofsale.application.command;


import dev.facturador.branch.application.command.delete.BranchDeleteCommand;
import dev.facturador.branch.domain.BranchID;
import dev.facturador.shared.application.commands.Command;
import lombok.Getter;

@Getter
public class PointOfSaleDeleteCommand extends Command {
    private Long pointOfSaleId;

    public PointOfSaleDeleteCommand(Long pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public static class Builder {
        private Long pointOfSaleId;

        public static PointOfSaleDeleteCommand.Builder getInstance() {
            return new PointOfSaleDeleteCommand.Builder();
        }

        public PointOfSaleDeleteCommand.Builder pointOfSaleId(Long pointOfSaleId) {
            this.pointOfSaleId = pointOfSaleId;
            return this;
        }

        public PointOfSaleDeleteCommand build() {
            return new PointOfSaleDeleteCommand(pointOfSaleId);
        }
    }
}
