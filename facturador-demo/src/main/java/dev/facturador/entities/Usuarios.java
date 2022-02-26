package dev.facturador.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
@Setter
public class Usuarios {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, length = 128, unique = true)
    private String email;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @JsonBackReference
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private AvatarUsuario avatarUser;

    public Usuarios(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}