package dev.facturador.global.domain;

import java.time.Clock;

public interface ClockProvider {
    Clock clock();
}
