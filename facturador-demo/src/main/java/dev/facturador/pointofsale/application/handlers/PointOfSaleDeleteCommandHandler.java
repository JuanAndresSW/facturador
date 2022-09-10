package dev.facturador.pointofsale.application.handlers;

import dev.facturador.global.domain.abstractcomponents.commands.CommandHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import dev.facturador.pointofsale.application.PointOfSaleRepository;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.commands.PointOfSaleDeleteCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manejador del comando {@link PointOfSaleDeleteCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class PointOfSaleDeleteCommandHandler
        implements CommandHandler<PointOfSaleDeleteCommand> {
    @Autowired
    private final PointOfSaleRepository repository;

    @Override
    public void handle(PointOfSaleDeleteCommand command) throws ResourceNotFound {
        //Verifica que el punto de venta exista para eliminarlo
        if (!repository.existsByPointOfSaleId(command.getPointOfSaleId())) {
            throw new ResourceNotFound("Esta punto de venta no existe");
        }

        repository.delete(PointOfSale.create(command.getPointOfSaleId()));
    }
}
