package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.mainaccount.domain.dto.GetResponse;
import dev.facturador.mainaccount.infrastructure.service.IMainAccountRecoverInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class RecoverInfoTraderMain {
    @Autowired
    private IMainAccountRecoverInfoService service;

    @PreAuthorize("hasAuthority('MAIN')")
    @GetMapping("/mainaccounts/{username}")
    public HttpEntity<?> delete(@PathVariable @NotEmpty String username){
        var user = service.getMainAccountByUsername(username);
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("No se ha encontrado un usuario relacionado a este Username");
        }
        GetResponse response = new GetResponse(
                user.get().getAccountOwner().getName(),
                user.get().getAccountOwner().getVat().getNameVat(),
                user.get().getAccountOwner().getUniqueKey());
        return ResponseEntity.ok().body(response);

    }
}
