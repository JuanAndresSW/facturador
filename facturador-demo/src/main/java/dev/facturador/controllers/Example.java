package dev.facturador.controllers;

import dev.facturador.entities.DetallesCuenta;
import dev.facturador.entitiesjson.Account;
import dev.facturador.services.ISingUpService;
import dev.facturador.services.SingUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public final class Example {

    @Autowired
    private ISingUpService service;

    @PostMapping("/api/recibe")
    public List<Account> getDataSingUp(@RequestBody Account requestDto){
        log.info(requestDto.toString());

        return service.getAll(requestDto);
    }
}