package dev.facturador.pointofsale.application.command;

import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.shared.application.commands.Command;
import lombok.Getter;

@Getter
public class PointOfSaleCreateCommand extends Command {
    private PointOfSaleCreate pointOfSaleCreate;

    public PointOfSaleCreateCommand(PointOfSaleCreate pointOfSaleCreate) {
        this.pointOfSaleCreate = pointOfSaleCreate;
    }

    public static class Builder {
        private PointOfSaleCreate pointOfSaleCreate;

        public static PointOfSaleCreateCommand.Builder getInstance() {
            return new PointOfSaleCreateCommand.Builder();
        }

        public PointOfSaleCreateCommand.Builder pointOfSaleCreate(PointOfSaleCreate pointOfSaleCreate) {
            this.pointOfSaleCreate = pointOfSaleCreate;
            return this;
        }

        public PointOfSaleCreateCommand build() {
            return new PointOfSaleCreateCommand(pointOfSaleCreate);
        }
    }
}
