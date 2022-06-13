package dev.facturador.branch.domain;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public final class BranchUpdate {
    @Size(min = 3, max = 30)
    private String updatedName;

    @Size(max = 128)
    @Email
    private String updatedEmail;

    @Size(max = 20)
    private String updatedPhone;
    private String updatedProvince;

    @Size(min = 4, max = 40)
    private String updatedDepartment;
    @Size(min = 4, max = 40)
    private String updatedLocality;
    @Size(max = 10)
    private String updatedPostalCode;

    @Size(min = 4, max = 50)
    private String updatedStreet;
    private String updatedNumberAddress;
    @Lob
    private String updatedLogo;
    @Lob
    private String updatedPhoto;
    @Size(min = 7, max = 7)
    private String updatedColor;
}
