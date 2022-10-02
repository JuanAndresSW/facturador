package dev.facturador.account.application.handlers;

import dev.facturador.account.application.AccountRepository;
import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.querys.GetAccountQuery;
import dev.facturador.global.domain.abstractcomponents.query.QueryHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Maneja la Query de {@link GetAccountQuery} retornado la entidad para ser mas generico
 */
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetAccountQueryHandler implements QueryHandler<Account, GetAccountQuery> {
    @Autowired
    private final AccountRepository repository;

    @Override
    public Account handle(GetAccountQuery query) throws ResourceNotFound {

        var user = repository.findByOwnerUserUsername(query.getUsername());

        if (user.isEmpty()) {
            throw new ResourceNotFound("No existe una cuenta con este username");
        }
        return user.get();
    }
}
