package dev.facturador.services.implementation;

import dev.facturador.entities.DetallesCuenta;
import dev.facturador.repository.IDetallesCuentaRepository;
import dev.facturador.services.IDetallesCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio del detalle de cuenta
 */
@Service
public final class DetallesCuentaService implements IDetallesCuentaService {

    //Injeccion de depencia del repository
    //Spring se encarga de:
    //buscar la implementacion, añadirla al contexto, crear el bean y hacer el new para que sea utilizable
    @Autowired
    private IDetallesCuentaRepository repository;

    @Override
    public DetallesCuenta findByUsername(DetallesCuenta details) {
        return (DetallesCuenta) repository.findByUsername(details.getUsername());
    }

    @Override
    public DetallesCuenta findById(DetallesCuenta details) {
        return null;
    }

    @Override
    public DetallesCuenta findByEmail(DetallesCuenta details) {
        return null;
    }

    @Override
    public boolean existDetailsAccount(DetallesCuenta details) {
        return false;
    }
}
