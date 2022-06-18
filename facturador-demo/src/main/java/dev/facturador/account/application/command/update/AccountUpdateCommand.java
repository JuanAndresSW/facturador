package dev.facturador.account.application.command.update;

import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.AccountUpdate;
import dev.facturador.global.application.commands.Command;
import lombok.Getter;

@Getter
public class AccountUpdateCommand extends Command {

    private final AccountUpdate accountUpdate;
    private final Account actualAccount;

    private AccountUpdateCommand(AccountUpdate accountUpdate, Account actualAccount) {
        this.accountUpdate = accountUpdate;
        this.actualAccount = actualAccount;
    }

    public static class Builder {
        private AccountUpdate accountUpdate;
        private Account actualAccount;

        public static AccountUpdateCommand.Builder getInstance() {
            return new AccountUpdateCommand.Builder();
        }

        public AccountUpdateCommand.Builder mainAccountUpdate(AccountUpdate accountUpdate) {
            this.accountUpdate = accountUpdate;
            return this;
        }

        public AccountUpdateCommand.Builder actualMainAccount(Account actualAccount) {
            this.actualAccount = actualAccount;
            return this;
        }

        public AccountUpdateCommand build() {
            return new AccountUpdateCommand(accountUpdate, actualAccount);
        }
    }
}
