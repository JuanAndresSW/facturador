package dev.facturador.services.abstracciones;


import dev.facturador.entities.CuentaPrincipal;

public interface ISingUpMainAccountService {
    void register(CuentaPrincipal mainAccount);
}
