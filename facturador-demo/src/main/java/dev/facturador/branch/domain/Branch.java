package dev.facturador.branch.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.facturador.branch.domain.subdomain.BranchLogo;
import dev.facturador.branch.domain.subdomain.BranchPhoto;
import dev.facturador.trader.domain.Trader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "branch")
@NoArgsConstructor
@Getter
@Setter
public final class Branch implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Id
    @Column(name = "id_branch")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long branchId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "province", nullable = false, length = 20)
    private String province;
    @Column(name = "department", nullable = false, length = 45)
    private String department;
    @Column(name = "locality", nullable = false, length = 45)
    private String locality;
    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;
    @Column(name = "street", nullable = false, length = 50)
    private String street;
    @Column(name = "number_adress", nullable = false, length = 5)
    private String numberAddress;

    @Column(name = "preference_color", nullable = false, length = 7)
    private String preferenceColor;
    @Column(name = "date_of_create", nullable = false, length = 7)
    private LocalDate dateOfCreate;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_trader", nullable = false)
    private Trader traderOwner;

    @JsonManagedReference
    @OneToOne(mappedBy = "branchIdForLogo", cascade =  CascadeType.ALL)
    private BranchLogo logo;

    @JsonManagedReference
    @OneToOne(mappedBy = "branchIdForPhoto", cascade =  CascadeType.ALL)
    private BranchPhoto photo;

    public Branch(String name,
                  String email,
                  String phone,
                  String province,
                  String department,
                  String locality,
                  String postalCode,
                  String street,
                  String preferenceColor){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.province = province;
        this.department = department;
        this.locality = locality;
        this.postalCode = postalCode;
        this.street = street;
        this.preferenceColor = preferenceColor;
    }

    public static Branch create(BranchCreate values) {
        var branch = new Branch(
                values.getName(),
                values.getEmail(),
                values.getPhone(),
                values.getAddress().getProvince(),
                values.getAddress().getDepartment(),
                values.getAddress().getLocality(),
                values.getAddress().getPostalCode(),
                values.getAddress().getStreet(),
                values.getPreferenceColor());

        if(values.getAddress().getNumberAdress() == 0){
            branch.setNumberAddress("S/N");
        } else{
            branch.setNumberAddress(String.valueOf(values.getAddress().getNumberAdress()));
        }

        if(values.getPhoto() != null){
            branch.setPhoto(new BranchPhoto(values.getPhoto()));
        }
        if(values.getLogo() != null){
            branch.setLogo(new BranchLogo(values.getLogo()));
        }

        branch.setDateOfCreate(LocalDate.now());
        branch.setTraderOwner(new Trader(values.getIDTrader()));

        return branch;
    }
}