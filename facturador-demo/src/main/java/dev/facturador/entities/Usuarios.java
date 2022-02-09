package dev.facturador.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor @Getter @Setter
public class Usuarios {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 320, unique = true)
    private String  email;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private AvatarUsuario avatarUser;

    public Usuarios(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}