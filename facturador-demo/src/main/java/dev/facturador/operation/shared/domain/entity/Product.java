package dev.facturador.operation.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @JsonIgnore
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_operation_parent", nullable = false, updatable = false, referencedColumnName = "id_operation")
    private Operation operationProduct;

    public Product(Integer quantity, Double price, String detail, Operation operationProduct) {
        this.quantity = quantity;
        this.price = price;
        this.detail = detail;
        this.operationProduct = operationProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder().append(getQuantity(), product.getQuantity()).append(getPrice(), product.getPrice()).append(getDetail(), product.getDetail()).isEquals();
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getQuantity()).append(getPrice()).append(getDetail()).toHashCode();
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
