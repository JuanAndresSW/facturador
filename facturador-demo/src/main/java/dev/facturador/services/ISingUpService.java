package dev.facturador.services;

import dev.facturador.entitiesjson.Account;

import java.util.List;

public interface ISingUpService {

    List<Account> getAll(Account account);
}