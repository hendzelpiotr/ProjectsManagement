package com.project.java.prz.common.core.time;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * Created by Piotr on 29.05.2017.
 */
@Component
public class CustomClock extends Clock {

    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.system(zone);
    }

    @Override
    public Instant instant() {
        return Instant.now();
    }

}
