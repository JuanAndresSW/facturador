package dev.facturador.mainaccount.application.command.register;

import dev.facturador.mainaccount.application.usecases.RegisterMainAccountUseCase;
import dev.facturador.mainaccount.domain.exception.IndexesAreRepeated;
import dev.facturador.shared.application.comandbus.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class MainAccountRegisterCommandHandler implements CommandHandler<MainAccountRegisterCommand> {
    private RegisterMainAccountUseCase useCase;

    public MainAccountRegisterCommandHandler(RegisterMainAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void handle(MainAccountRegisterCommand command) throws Exception {
        String message = useCase.returnAnErrorMessageWhenIndexesAreRepeated(command.getMainAccountRegister());
        if (StringUtils.hasText(message)) {
            throw new IndexesAreRepeated(message);
        }
        useCase.handle(command.getMainAccountRegister());
    }
}
