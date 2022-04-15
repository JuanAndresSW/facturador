package dev.facturador.branch.domain.subdomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.branch.domain.Branch;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "branch_photo")
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class BranchPhoto implements Serializable {
    public static final Long serialVersinUID = 1L;

    @JsonIgnore
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch", nullable = false)
    private Branch branchIdForPhoto;

    @Column(name = "photo", nullable = false)
    @Lob
    private String photo;

    public BranchPhoto(String photo, Branch branchIdForPhoto) {
        this.photo = photo;
        this.branchIdForPhoto = branchIdForPhoto;
    }
}
