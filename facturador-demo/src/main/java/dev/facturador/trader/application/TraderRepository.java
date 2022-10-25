package dev.facturador.trader.application;

import dev.facturador.trader.domain.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

public interface TraderRepository extends JpaRepository<Trader, Long> {
    /*
    @Query("SELECT B.branchId, B.fantasyName, B.locality, B.street, B.addressNumber, P.pointOfSaleId, P.pointOfSaleNumber FROM Trader T INNER JOIN Branch B ON T.traderId = B.traderOwner.traderId LEFT JOIN PointOfSale P ON B.branchId = P.branchOwner.branchId WHERE T.traderId = :traderID")

    @Query(value = "SELECT id_branch, fantasy_name, locality, street, address_number, id_point_of_sale, point_of_sale_number \n" +
            "FROM facturador_db.trader AS T \n" +
            "INNER JOIN facturador_db.branch AS B\n" +
            "ON T.id_trader = B.id_owner_trader\n" +
            "LEFT JOIN facturador_db.point_of_sale AS P \n" +
            "ON B.id_branch = P.id_owner_branch\n" +
            "where id_trader = :traderID", nativeQuery = true)

     @Query("SELECT B.branchId, B.fantasyName, B.locality, B.street, B.addressNumber, P.pointOfSaleId, P.pointOfSaleNumber FROM Trader T INNER JOIN Branch B ON T.traderId = B.traderOwner.traderId LEFT JOIN PointOfSale P ON B.branchId = P.branchOwner.branchId WHERE T.traderId = :traderID")
    */
    Boolean existsByTraderId(Long traderId);
}
