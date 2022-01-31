package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Entity
@Table(name = "recibo_x")
@NoArgsConstructor @Getter @Setter @ToString
public final class ReciboX {

    @Id
    @Column(name = "id_recibo_x")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReciboX;

    @Column(name = "num_recibo_x", nullable = false)
    private int numFactura;

    @Column(nullable = false, length = 1)
    private String flux;

    @Column(name = "fecha_emision", nullable = false)
    private Date issueDate;

    @Column(nullable = false, length = 40)
    private String pagador;

    @Column(name = "horario", nullable = false)
    private LocalDateTime horario;

    @Column(name = "cheque", nullable = false, scale = 2)
    private double cheque;

    @Column(name = "documentos", nullable = false, scale = 2)
    private double documentos;

    @Column(name = "efectivo", nullable = false, scale = 2)
    private double efectivo;

    @Column(name = "domicilio_pago", nullable = false, length = 20)
    private String domicilioPago;
}