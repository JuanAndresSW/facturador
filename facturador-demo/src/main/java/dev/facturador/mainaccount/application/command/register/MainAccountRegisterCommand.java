package dev.facturador.mainaccount.application.command.register;

import dev.facturador.mainaccount.domain.MainAccountRegister;
import dev.facturador.shared.application.commands.Command;
import lombok.ToString;

@ToString
public class MainAccountRegisterCommand extends Command {
    private MainAccountRegister mainAccountRegister;

    private MainAccountRegisterCommand(MainAccountRegister mainAccountRegister) {
        this.mainAccountRegister = mainAccountRegister;
    }

    public MainAccountRegister getMainAccountRegister() {
        return this.mainAccountRegister;
    }

    public static class Builder {
        private MainAccountRegister mainAccountRegister;

        public static MainAccountRegisterCommand.Builder getInstance() {
            return new MainAccountRegisterCommand.Builder();
        }

        public MainAccountRegisterCommand.Builder mainAccountRegister(MainAccountRegister mainAccountRegister) {
            this.mainAccountRegister = mainAccountRegister;
            return this;
        }

        public MainAccountRegisterCommand build() {
            return new MainAccountRegisterCommand(mainAccountRegister);
        }
    }
}
