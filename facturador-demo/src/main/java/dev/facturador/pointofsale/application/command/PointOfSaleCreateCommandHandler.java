package dev.facturador.pointofsale.application.command;

import dev.facturador.shared.application.comandbus.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class PointOfSaleCreateCommandHandler implements CommandHandler<PointOfSaleCreateCommand> {

    @Override
    public void handle(PointOfSaleCreateCommand command) throws Exception {
        var trader = command.getTrader();
        trader.getPointOfSaleOutlets();
    }
}
