package dev.facturador.mainaccount.application.command.delete;

import dev.facturador.mainaccount.domain.MainAccountIdUsername;
import dev.facturador.shared.application.comandbus.Command;

public class MainAccountDeleteCommand extends Command {
    private MainAccountIdUsername mainAccountIdUsername;

    public MainAccountDeleteCommand(MainAccountIdUsername mainAccountIdUsername) {
        this.mainAccountIdUsername = mainAccountIdUsername;
    }

    public MainAccountIdUsername getMainAccountIdUsername() {
        return this.mainAccountIdUsername;
    }

    public static class Builder {
        private MainAccountIdUsername mainAccountIdUsername;

        public static MainAccountDeleteCommand.Builder getInstance() {
            return new MainAccountDeleteCommand.Builder();
        }

        public MainAccountDeleteCommand.Builder mainAccountIdUsername(MainAccountIdUsername mainAccountIdUsername) {
            this.mainAccountIdUsername = mainAccountIdUsername;
            return this;
        }

        public MainAccountDeleteCommand build() {
            return new MainAccountDeleteCommand(mainAccountIdUsername);
        }
    }
}
