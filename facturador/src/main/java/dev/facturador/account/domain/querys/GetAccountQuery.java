package dev.facturador.account.domain.querys;

import dev.facturador.account.domain.Account;
import dev.facturador.global.domain.abstractcomponents.query.Query;
import lombok.Builder;
import lombok.Data;

/**
 * Query para recuperar cuenta
 */
@Data
@Builder
public class GetAccountQuery extends Query<Account> {
    private final String username;
}
