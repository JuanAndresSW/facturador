package dev.facturador.mainaccount.application.command.update;

import dev.facturador.mainaccount.application.usecases.UpdateMainAccountUseCase;
import dev.facturador.mainaccount.domain.exception.ErrorInDataForUpdate;
import dev.facturador.shared.application.comandbus.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MainAccountUpdateCommandHandler implements CommandHandler<MainAccountUpdateCommand> {

    private UpdateMainAccountUseCase useCase;

    public MainAccountUpdateCommandHandler(UpdateMainAccountUseCase useCase){
        this.useCase = useCase;
    }

    @Override
    public void handle(MainAccountUpdateCommand command) throws ErrorInDataForUpdate {
        var result = useCase.verifyData(command.getMainAccountUpdate(), command.getMainAccount());
        if (StringUtils.hasText(result)) {
            throw new ErrorInDataForUpdate(result);
        }

        useCase.handle(command.getMainAccountUpdate(), command.getMainAccount());
    }
}
