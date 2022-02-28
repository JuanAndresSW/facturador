package dev.facturador.branchaccount.infrastructure;

import dev.facturador.gategay.responsecore.IApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class BranchAccountController {
    private static final String REGISTER_SECONDARY = "/secondaryaccounts";
    @Autowired
    private IBranchAccountService secondaryAccountService;

    /**
     * Registrar Cuenta Secundaria
     * No hay implementacion. Solo se sabe que solo usuario MAIN pueden hacer esto
     */
    @PostMapping(REGISTER_SECONDARY)
    @PreAuthorize("MAIN")
    public HttpEntity<? extends IApiResponse> registerSecondary() {
        //No necesita implementacion por ahora
        return null;
    }
}
