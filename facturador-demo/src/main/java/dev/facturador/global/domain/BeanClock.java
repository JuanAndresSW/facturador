package dev.facturador.global.domain;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Service
public class BeanClock implements ClockProvider {
    @Override
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.of("GMT"));
    }
}
