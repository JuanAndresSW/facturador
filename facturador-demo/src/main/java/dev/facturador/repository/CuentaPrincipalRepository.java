package dev.facturador.repository;

import dev.facturador.entities.CuentaPrincipal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaPrincipalRepository extends CrudRepository<CuentaPrincipal, Integer> {
}
