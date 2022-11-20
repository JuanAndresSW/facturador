package dev.facturador.pointofsale.domain.commands;

import dev.facturador.global.domain.abstractcomponents.command.Command;
import dev.facturador.pointofsale.domain.PointOfSaleModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Comando para crear un punto de venta
 */
@AllArgsConstructor
@Getter
public class PointOfSaleCreateCommand extends Command {
    private final PointOfSaleModel pointOfSaleModel;

    /**
     * Builder del comando
     */
    public static class Builder {
        private PointOfSaleModel pointOfSaleModel;

        public static PointOfSaleCreateCommand.Builder getInstance() {
            return new PointOfSaleCreateCommand.Builder();
        }

        public PointOfSaleCreateCommand.Builder pointOfSaleCreate(PointOfSaleModel pointOfSaleModel) {
            this.pointOfSaleModel = pointOfSaleModel;
            return this;
        }

        public PointOfSaleCreateCommand build() {
            return new PointOfSaleCreateCommand(pointOfSaleModel);
        }
    }
}
