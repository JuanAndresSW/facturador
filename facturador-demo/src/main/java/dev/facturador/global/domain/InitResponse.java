package dev.facturador.global.domain;

//Record para el output de InitResource
public record InitResponse(String username, Long IDTrader, Integer actives, Integer passives) {
}
