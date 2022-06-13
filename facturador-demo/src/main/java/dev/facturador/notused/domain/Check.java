package dev.facturador.notused.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "check")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class Check implements Serializable {
    public static final Long serialVersinUID = 1L;

    @Id
    @Column(name = "id_check")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCheck;

    @Column(name = "num_check", nullable = false)
    private int numCheck;

    @JoinColumns(value = {
            @JoinColumn(name = "id_point_of_sale_issuing", referencedColumnName = "id_point_of_sale_issuing", nullable = false),
            @JoinColumn(name = "date_of_issue", referencedColumnName = "date_of_issue", nullable = false)
    })
    @OneToOne(cascade = CascadeType.ALL)
    private Operation dataOperation;

    @Column(name = "amount", nullable = false, scale = 2)
    private double amount;

    @Column(name = "bank", nullable = false, length = 20)
    private String bankName;

    @Column(name = "crossed", nullable = false)
    private boolean crossed;

    @Column(name = "series", nullable = false, length = 1)
    private String serie;


}