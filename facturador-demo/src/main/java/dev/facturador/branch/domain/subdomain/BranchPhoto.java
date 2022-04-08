package dev.facturador.branch.domain.subdomain;

import dev.facturador.branch.domain.Branch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "branch_photo")
@Setter
@Getter
@NoArgsConstructor
@ToString
public final class BranchPhoto implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch", nullable = false)
    private Branch branchIdForPhoto;

    @Column(name = "photo", nullable = false)
    @Lob
    private String photo;

    public BranchPhoto(String photo){
        this.photo = photo;
    }
}
