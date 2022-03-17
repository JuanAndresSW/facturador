package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;
import dev.facturador.mainaccount.infrastructure.service.IMainAccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class UpdateMain {
    @Autowired
    private IMainAccountUpdateService service;

    //@PreAuthorize("hasRole('MAIN')")
    @PreAuthorize("hasAuthority('MAIN')")
    @PutMapping("/mainaccounts")
    public HttpEntity<?> updateAccount(@Valid @RequestBody UpdateRequest tryUpdate) {
        var user = service.getMainAccountByUsername(tryUpdate.getUser().username());
        String message = service.verifyData(tryUpdate, user);
        if(StringUtils.hasText(message)){
            return ResponseEntity.badRequest().body(message);
        }

        service.update(tryUpdate, user);

        return ResponseEntity.ok().body("Success Update");
    }
}
