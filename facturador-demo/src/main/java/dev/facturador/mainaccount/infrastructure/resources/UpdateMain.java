package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;
import dev.facturador.mainaccount.infrastructure.IMainAccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        var user = service.verifyIfCotainsNewPassword(tryUpdate);
        if(user == null){
            return ResponseEntity.badRequest().body("La contraseña antigua no es correcta");
        }
        var errorMessages = service.verifyUsernameAndCodeNotExists(tryUpdate);
        if(errorMessages.size() >= 1){
            return ResponseEntity.badRequest().body(errorMessages);
        }
        service.update(tryUpdate, user);
        return ResponseEntity.ok().body("Success Update");
    }
}
