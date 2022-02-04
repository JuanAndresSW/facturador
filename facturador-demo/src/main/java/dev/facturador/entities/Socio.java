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
    private long idSocio;

    @Column(name = "cuit", length = 15)
    private String uniqueKey;

    @Column(name = "direccion", nullable = false, length = 50)
    private String address;
    @Column(name = "nombre", nullable = false, length = 30)
    private String name;
    @Column(name = "telefono", nullable = false)
    private String phoneNumber;
    @Column(name = "iva", nullable = false)
    private String vat;
    @Column(name = "cp", nullable = false)
    private String cp;

    @Column(name = "web", length = 320)
    private String web;

    public Socio(long idSocio) {
        this.idSocio = idSocio;
    }
}