package dev.facturador.pointofsale.application.subdomain.handlers;

import dev.facturador.global.domain.abstractcomponents.commands.CommandHandler;
import dev.facturador.pointofsale.application.subdomain.PointsOfSaleControlRepository;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPosUpdateCommand;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class ControlOfPosUpdateCommandHandler implements CommandHandler<ControlOfPosUpdateCommand> {
    private final PointsOfSaleControlRepository repository;

    @Override
    public void handle(ControlOfPosUpdateCommand command)
            throws Exception {
        repository.saveAndFlush(PointsOfSaleControl.create(command.getData()));
    }
}
