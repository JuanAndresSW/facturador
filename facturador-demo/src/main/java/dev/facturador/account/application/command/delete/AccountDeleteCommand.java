package dev.facturador.account.application.command.delete;

import dev.facturador.global.application.commands.Command;

/**Comando para eliminar la cuenta*/
public class AccountDeleteCommand extends Command {
    private final String username;

    public AccountDeleteCommand(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
    /**Builder del comando*/
    public static class Builder {
        private String username;

        public static AccountDeleteCommand.Builder getInstance() {
            return new AccountDeleteCommand.Builder();
        }

        public AccountDeleteCommand.Builder username(String username) {
            this.username = username;
            return this;
        }

        public AccountDeleteCommand build() {
            return new AccountDeleteCommand(username);
        }
    }
}
