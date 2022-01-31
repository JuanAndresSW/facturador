package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "socio")
@NoArgsConstructor @Getter @Setter @ToString
public final class Socio {

    @Id
    @Column(name = "id_socio", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSocio;

    private String cuit;

    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String iva;
    @Column(nullable = false)
    private String cp;

    private String web;

    public Socio(Integer idSocio) {
        this.idSocio = idSocio;
    }
}