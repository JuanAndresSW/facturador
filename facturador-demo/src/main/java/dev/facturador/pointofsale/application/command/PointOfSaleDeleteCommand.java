package dev.facturador.pointofsale.application.command;


import dev.facturador.global.application.commands.Command;
import lombok.Getter;

/**Comando para eliminar un punto de venta*/
@Getter
public class PointOfSaleDeleteCommand extends Command {
    private final Long pointOfSaleId;

    public PointOfSaleDeleteCommand(Long pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }
    /**Builder del comando*/
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
