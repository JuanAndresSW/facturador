package dev.facturador.repository;

import dev.facturador.entities.Comerciante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComercianteRepository extends JpaRepository<Comerciante, Long> {
    Boolean existsByUniqueKey(String uniqueKey);
}
