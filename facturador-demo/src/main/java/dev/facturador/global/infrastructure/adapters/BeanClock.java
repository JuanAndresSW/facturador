package dev.facturador.global.infrastructure.adapters;

import dev.facturador.global.domain.ClockProvider;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class BeanClock implements ClockProvider {
    @Override
    public Clock clock() {
        return Clock.fixed(Clock.systemUTC().instant(), ZoneOffset.UTC);
    }
}
