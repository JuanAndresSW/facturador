package dev.facturador.branch.domain.subdomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.facturador.branch.domain.Branch;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "branch_logo")
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public final class BranchLogo implements Serializable {
    public static final Long serialVersinUID = 1L;

    @JsonIgnore
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_branch", nullable = false)
    private Branch branchIdForLogo;

    @Column(name = "logo", nullable = false)
    @Lob
    private String logo;

    public BranchLogo(String logo, Branch branchIdForLogo) {
        this.logo = logo;
        this.branchIdForLogo = branchIdForLogo;
    }
}