package dev.facturador.notused.domain.thirdparty.domain;

import dev.facturador.global.domain.VatCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("ALL")
@Entity
@Table(name = "third_party")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ThirdParty implements Serializable {
    public static final Long serialVersinUID = 1L;
    @Id
    @Column(name = "id_third_party", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thirdPartyId;

    @Column(nullable = false, length = 128)
    private String email;
    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 20)
    private String province;
    @Column(nullable = false, length = 45)
    private String department;
    @Column(nullable = false, length = 45)
    private String locality;
    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;
    @Column(nullable = false, length = 50)
    private String street;
    @Column(name = "address_number", nullable = false, length = 5)
    private String addressNumber;

    @Column(name = "branch_name", length = 20, nullable = false)
    private String branchName;

    @Column(name = "vat_category", nullable = false,
            columnDefinition = "enum('REGISTERED_RESPONSIBLE','MONOTAX_RESPONSIBLE')")
    @Enumerated(value = EnumType.STRING)
    private VatCategory vatCategory;

    @Column(name = "usage_counter", nullable = false)
    private int usageCounter;

}