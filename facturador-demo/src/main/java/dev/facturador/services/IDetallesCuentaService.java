package dev.facturador.services;

import dev.facturador.entities.DetallesCuenta;

public interface IDetallesCuentaService {

    DetallesCuenta findByUsername(DetallesCuenta details);
    DetallesCuenta findById(DetallesCuenta details);
    DetallesCuenta findByEmail(DetallesCuenta details);
    boolean existDetailsAccount(DetallesCuenta details);
}
