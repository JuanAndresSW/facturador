package dev.facturador.account.application.command.register;

import dev.facturador.account.domain.AccountRegister;
import dev.facturador.global.application.commands.Command;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountRegisterCommand extends Command {
    private final AccountRegister accountRegister;

    private AccountRegisterCommand(AccountRegister accountRegister) {
        this.accountRegister = accountRegister;
    }

    public static class Builder {
        private AccountRegister accountRegister;

        public static AccountRegisterCommand.Builder getInstance() {
            return new AccountRegisterCommand.Builder();
        }

        public AccountRegisterCommand.Builder mainAccountRegister(AccountRegister accountRegister) {
            this.accountRegister = accountRegister;
            return this;
        }

        public AccountRegisterCommand build() {
            return new AccountRegisterCommand(accountRegister);
        }
    }
}
