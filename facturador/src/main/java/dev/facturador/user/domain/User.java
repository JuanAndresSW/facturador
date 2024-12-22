package dev.facturador.user.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.facturador.user.domain.subdomain.UserAvatar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, length = 128, unique = true)
    private String email;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @JsonBackReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAvatar userAvatar;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", updatedUsername='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}