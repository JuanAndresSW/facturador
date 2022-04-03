package dev.facturador.pointofsale.infrastructure;

import dev.facturador.mainaccount.domain.MainAccountTraderData;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.shared.application.comandbus.CommandBus;
import dev.facturador.trader.domain.Trader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api/pointofsales")
public class CreatePointOfSaleResource {

    private CommandBus commandBus;

    public CreatePointOfSaleResource(CommandBus commandBus){
        this.commandBus = commandBus;
    }

    @PostMapping
    public HttpEntity<?> create(@Valid @RequestBody PointOfSaleCreate values, HttpServletRequest request){
        String username = values.getUsername();

        var client = WebClient.builder().baseUrl("http://localhost:8080").build();
        var responseEntity = client.get()
                .uri("/api/mainaccounts/trader/"+username)
                .header("Authorization", request.getHeader("Authorization"))
                .accept(MediaType.ALL).retrieve().toEntity(Trader.class).block();
        var trader = responseEntity.getBody();


        return ResponseEntity.ok().build();
    }
}
