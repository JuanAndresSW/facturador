package dev.facturador.pointofsale.application.usecase;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Caso de uso para eliminar un punto de venta*/
@Service
public class DeletePointOfSaleUseCase {
    @Autowired
    private PointOfSaleRepository repository;

    public void handlerPointOfSaleDelete(Long pointOfSaleId) {
        var pointofsale = PointOfSale.create(pointOfSaleId);
        repository.delete(pointofsale);
    }
    /**Verifica que el punto de venta si exista*/
    public boolean verify(Long pointOfSaleId) {
        return repository.existsByPointOfSaleId(pointOfSaleId);
    }
}
