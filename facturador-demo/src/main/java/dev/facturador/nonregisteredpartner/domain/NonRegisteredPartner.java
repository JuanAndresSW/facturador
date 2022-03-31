package dev.facturador.nonregisteredpartner.domain;

import dev.facturador.shared.domain.shared.Vat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("ALL")
@Entity
@Table(name = "non_registered_partner")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class NonRegisteredPartner implements Serializable {
    public static final Long serialVersinUID = 1L;
    @Id
    @Column(name = "id_non_registred_partner", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partnerUnTrueId;

    @Column(name = "unique_key", length = 15, nullable = false)
    private String uniqueKey;

    @Column(name = "name_point_of_sale", length = 20, nullable = false)
    private String namePointOfSale;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "locality", length = 20, nullable = false)
    private String lacation;

    @Column(name = "postal_code", length = 10, nullable = false)
    private String cp;

    @Column(name = "vatCategory", nullable = false,
            columnDefinition = "enum('RESPONSABLE_INSCRIPTO','MONOTRIBUTISTA','SUJETO_EXENTO')")
    @Enumerated(value = EnumType.STRING)
    private Vat vat;
    @Column(name = "use_account", nullable = false)
    private int numUse;

}