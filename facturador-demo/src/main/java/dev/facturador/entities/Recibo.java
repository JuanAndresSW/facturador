package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@SuppressWarnings("ALL")
@Entity
@Table(name = "recibo")
@NoArgsConstructor @Getter @Setter @ToString
public final class Recibo {

    @Id
    @Column(name = "id_recibo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecibo;

    @Column(name = "fecha_emision", nullable = false)
    private Date issueDate;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String pagador;

    @Column(nullable = false)
    private String domicilio;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int amount;

    public Recibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }
}