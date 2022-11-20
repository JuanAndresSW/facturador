package dev.facturador.branch.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.global.domain.Address;
import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad Sucursal
 */
@Entity
@Table(name = "branch")
@NoArgsConstructor
@Getter
@Setter
public final class Branch implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "id_branch")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(name = "fantasy_name", nullable = false, length = 30)
    private String fantasyName;
    @Column(nullable = false, length = 128)
    private String email;
    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 20)
    private String province;
    @Column(nullable = false, length = 45)
    private String city;
    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;
    @Column(nullable = false, length = 50)
    private String street;
    @Column(name = "address_number", nullable = false, length = 5)
    private String addressNumber;

    @Column(name = "preference_color", nullable = false, length = 7)
    private String preferenceColor;
    @Column(name = "creation_date", nullable = false, length = 7)
    private LocalDate createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_owner_trader", nullable = false, updatable = false, referencedColumnName = "id_trader")
    private Trader traderOwner;

    @Lob
    @Column(nullable = false)
    private String logo;

    @Lob
    @Column(nullable = false)
    private String photo;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "branchOwner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PointOfSale> pointsOfSale;

    public Branch(long branchId) {
        super();
        this.branchId = branchId;
    }

    public Branch(Long branchId, String fantasyName, String city, String street, String addressNumber, List<PointOfSale> pointsOfSale) {
        this.branchId = branchId;
        this.fantasyName = fantasyName;
        this.city = city;
        this.street = street;
        this.addressNumber = addressNumber;
        this.pointsOfSale = pointsOfSale;
    }

    public Branch(String fantasyName,
                  String email,
                  String phone,
                  String province,
                  String city,
                  String postalCode,
                  String street,
                  String preferenceColor) {
        this.fantasyName = fantasyName;
        this.email = email;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.preferenceColor = preferenceColor;
    }

    /**
     * Named Contructor con solo el Id
     */
    public static Branch create(Long value) {
        return new Branch(value);
    }

    /**
     * NamedContructor para el update
     */
    public static Branch create(BranchUpdateRestModel updatedValues, Branch branch) {
        if (StringUtils.hasText(updatedValues.getUpdatedName())) {
            branch.setFantasyName(updatedValues.getUpdatedName());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedEmail())) {
            branch.setEmail(updatedValues.getUpdatedEmail());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedPhone())) {
            branch.setPhone(updatedValues.getUpdatedPhone());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedProvince())) {
            branch.setProvince(updatedValues.getUpdatedProvince());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedCity())) {
            branch.setCity(updatedValues.getUpdatedCity());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedPostalCode())) {
            branch.setPostalCode(updatedValues.getUpdatedPostalCode());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedStreet())) {
            branch.setStreet(updatedValues.getUpdatedStreet());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedAddressNumber())) {
            branch.setAddressNumber(updatedValues.getUpdatedAddressNumber());
        }

        if (StringUtils.hasText(updatedValues.getUpdatedPhoto())) {
            branch.setPhoto(updatedValues.getUpdatedPhoto());
        }
        if (StringUtils.hasText(updatedValues.getUpdatedLogo())) {
            branch.setLogo(updatedValues.getUpdatedLogo());
        }

        if (StringUtils.hasText(updatedValues.getUpdatedPreferenceColor())) {
            branch.setPreferenceColor(updatedValues.getUpdatedPreferenceColor());
        }

        return branch;
    }

    /**
     * Named Contructor para crear
     */
    public static Branch create(BranchCreateRestModel values) {
        var branch = new Branch(
                values.getName(),
                values.getEmail(),
                values.getPhone(),
                values.getAddress().getProvince(),
                values.getAddress().getCity(),
                values.getAddress().getPostalCode(),
                values.getAddress().getStreet(),
                values.getPreferenceColor());

        branch.setAddressNumber(defineAddressNumber(values.getAddress()));

        if (StringUtils.hasText(values.getPhoto())) {
            branch.setPhoto(values.getPhoto());
        } else {
            branch.setPhoto("undefined");
        }

        if (StringUtils.hasText(values.getLogo())) {
            branch.setLogo(values.getLogo());
        } else {
            branch.setLogo("undefined");
        }

        branch.setCreatedAt(LocalDate.now());
        branch.setTraderOwner(new Trader(values.getIDTrader()));

        return branch;
    }

    public static String defineAddressNumber(Address address) {
        if (address.getAddressNumber() != 0) {
            return String.valueOf(address.getAddressNumber());
        }
        return "S/N";
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId=" + branchId +
                ", name='" + fantasyName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", locality='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", addressNumber='" + addressNumber + '\'' +
                ", preferenceColor='" + preferenceColor + '\'' +
                ", createdAt=" + createdAt +
                ", logo=" + logo +
                ", photo=" + photo +
                '}';
    }
}