package dev.facturador.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_avatar")
@NoArgsConstructor
@Getter
@Setter
@ToString
public final class UserAvatar {
    @Id
    @Column(name = "id_user_avatar")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userAvatarId;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User user;

    public UserAvatar(String avatar, User user) {
        this.avatar = avatar;
        this.user = user;
    }
}
