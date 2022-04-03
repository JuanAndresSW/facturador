package dev.facturador.mainaccount.infrastructure;

import dev.facturador.mainaccount.application.query.MainAccountGetQuery;
import dev.facturador.mainaccount.domain.MainAccountIdUsername;
import dev.facturador.mainaccount.domain.MainAccountTraderData;
import dev.facturador.shared.application.querybus.QueryBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Slf4j
@RestController
@RequestMapping(path = "/api/mainaccounts")
public final class GetTraderResource {
    private QueryBus queryBus;

    public GetTraderResource(QueryBus queryBus){
        this.queryBus = queryBus;
    }

    @PreAuthorize("hasAuthority('MAIN')")
    @GetMapping("/trader/{username}")
    public HttpEntity<?> getTrader(@PathVariable @NotEmpty String username) throws Exception {
        MainAccountGetQuery query = MainAccountGetQuery.Builder.getInstance()
                .mainAccountIdUsername(MainAccountIdUsername.starter(username)).build();

        var user = queryBus.handle(query);

        return ResponseEntity.ok().body(user.getAccountOwner());
    }
}
