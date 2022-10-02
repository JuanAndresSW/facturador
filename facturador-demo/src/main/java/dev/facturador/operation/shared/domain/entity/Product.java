package dev.facturador.operation.shared.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidad Producto
 */
@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
public final class Product implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false, length = 30)
    private String detail;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH})
    @JoinColumn(name = "id_operation_parent", nullable = false, updatable = false, referencedColumnName = "id_operation")
    private Operation operationProduct;

    public Product(Integer quantity, Double price, String detail, Operation operationProduct) {
        this.quantity = quantity;
        this.price = price;
        this.detail = detail;
        this.operationProduct = operationProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                '}';
    }
}
