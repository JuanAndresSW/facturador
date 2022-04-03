package dev.facturador.pointofsale.application.command;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.pointofsale.domain.PointOfSaleLinkWithTrader;
import dev.facturador.shared.application.comandbus.Command;
import dev.facturador.trader.domain.Trader;

public class PointOfSaleCreateCommand extends Command {
    private PointOfSaleCreate pointOfSaleCreate;
    private Trader trader;

    public PointOfSaleCreateCommand(PointOfSaleCreate pointOfSaleCreate, Trader trader){
        this.pointOfSaleCreate = pointOfSaleCreate;
        this.trader = trader;
    }

    public PointOfSaleCreate getPointOfSaleCreate(){
        return this.pointOfSaleCreate;
    }
    public Trader getTrader(){
        return this.trader;
    }

    public static class Builder{
        private PointOfSaleCreate pointOfSaleCreate;
        private Trader trader;

        public static PointOfSaleCreateCommand.Builder getInstance() {
            return new PointOfSaleCreateCommand.Builder();
        }

        public PointOfSaleCreateCommand.Builder pointOfSaleCreate(PointOfSaleCreate pointOfSaleCreate) {
            this.pointOfSaleCreate = pointOfSaleCreate;
            return this;
        }
        public PointOfSaleCreateCommand.Builder pointOfSaleCreate(Trader trader) {
            this.trader = trader;
            return this;
        }

        public PointOfSaleCreateCommand build() {
            return new PointOfSaleCreateCommand(pointOfSaleCreate, trader);
        }
    }
}
