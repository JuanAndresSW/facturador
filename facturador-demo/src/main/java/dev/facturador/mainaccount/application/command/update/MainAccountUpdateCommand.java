package dev.facturador.mainaccount.application.command.update;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountUpdate;
import dev.facturador.shared.application.commands.Command;

public class MainAccountUpdateCommand extends Command {

    private MainAccountUpdate mainAccountUpdate;
    private MainAccount actualMainAccount;

    private MainAccountUpdateCommand(MainAccountUpdate mainAccountUpdate, MainAccount actualMainAccount) {
        this.mainAccountUpdate = mainAccountUpdate;
        this.actualMainAccount = actualMainAccount;
    }

    public MainAccountUpdate getMainAccountUpdate() {
        return this.mainAccountUpdate;
    }

    public MainAccount getMainAccount() {
        return this.actualMainAccount;
    }

    public static class Builder {
        private MainAccountUpdate mainAccountUpdate;
        private MainAccount actualMainAccount;

        public static MainAccountUpdateCommand.Builder getInstance() {
            return new MainAccountUpdateCommand.Builder();
        }

        public MainAccountUpdateCommand.Builder mainAccountUpdate(MainAccountUpdate mainAccountUpdate) {
            this.mainAccountUpdate = mainAccountUpdate;
            return this;
        }

        public MainAccountUpdateCommand.Builder actualMainAccount(MainAccount actualMainAccount) {
            this.actualMainAccount = actualMainAccount;
            return this;
        }

        public MainAccountUpdateCommand build() {
            return new MainAccountUpdateCommand(mainAccountUpdate, actualMainAccount);
        }
    }
}
