package com.gribov;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "timer.enabled", havingValue = "true")
public class TimerAspectConfiguration {
    @Bean
    public TimerAspect timerAspect() {
        return new TimerAspect();
    }
}
