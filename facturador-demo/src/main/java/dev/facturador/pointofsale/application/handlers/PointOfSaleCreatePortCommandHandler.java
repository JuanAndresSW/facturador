package dev.facturador.pointofsale.application.handlers;

import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import dev.facturador.pointofsale.application.PointOfSaleRepository;
import dev.facturador.pointofsale.application.subdomain.PointsOfSaleControlRepository;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.commands.PointOfSaleCreateCommand;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import dev.facturador.pointofsale.domain.subdomain.RequiredPosControlData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manejador del comando {@link PointOfSaleCreateCommand}
 */
@AllArgsConstructor
@Service
@Transactional
public class PointOfSaleCreatePortCommandHandler implements PortCommandHandler<PointOfSaleCreateCommand> {
    @Autowired
    private final PointOfSaleRepository repository;
    @Autowired
    private final PointsOfSaleControlRepository controlRepository;

    /**
     * Sede la operacion al caso de uso
     */
    @Override
    public void handle(PointOfSaleCreateCommand command) throws Exception {
        var pointofsale = PointOfSale.create(command.getPointOfSaleModel());
        //Se crea el punto de venta
        repository.save(pointofsale);
        //Se actualiza el PointOfSaleControl
        controlRepository.saveAndFlush(PointsOfSaleControl.create(RequiredPosControlData.starter(
                command.getPointOfSaleModel().posControl().getPointsOfSaleControlId(),
                command.getPointOfSaleModel().posControl().getCurrentCount(),
                command.getPointOfSaleModel().posControl().getTotalCount(),
                true)));
    }
}
