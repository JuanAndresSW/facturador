package dev.facturador.mainaccount.infrastructure.resources;

import dev.facturador.mainaccount.infrastructure.IMainAccountDeleteService;
import dev.facturador.mainaccount.infrastructure.IMainAccountRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class DeleteMain {
    @Autowired
    private IMainAccountDeleteService service;

    @PreAuthorize("hasAuthority('MAIN')")
    @DeleteMapping("/mainaccounts/{username}")
    public HttpEntity<String> delete(@PathVariable @NotEmpty String username){
        if(!service.existsByUsername(username)){
            return ResponseEntity.badRequest().body("Usuario no existe");
        }
        service.deleteByUsername(username);
        return ResponseEntity.ok().body("Usuario eliminado");
    }
}
