package dev.facturador.services;

import dev.facturador.entitiesjson.Account;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public final class SingUpService implements ISingUpService{

    @Override
    public List<Account> getAll(Account account){
        List<Account> lista = new LinkedList<>();
        lista.add(account);
        lista.add(account);
        lista.add(new Account());

        return lista;
    }
}
