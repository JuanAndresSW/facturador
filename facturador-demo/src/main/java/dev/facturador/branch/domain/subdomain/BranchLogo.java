package dev.facturador.branch.domain.subdomain;

import dev.facturador.branch.domain.Branch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "branch_logo")
@Setter
@Getter
@NoArgsConstructor
@ToString
public final class BranchLogo implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch", nullable = false)
    private Branch branchIdForLogo;

    @Column(name = "logo", nullable = false)
    @Lob
    private String logo;

    public BranchLogo(String logo){
        this.logo = logo;
    }
}