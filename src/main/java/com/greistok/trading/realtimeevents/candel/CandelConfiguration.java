package com.greistok.trading.realtimeevents.candel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CandelConfiguration {

    @Bean
    public CandelGenerator minuteCandelGenerator() {
        return new CandelGenerator(1);
    }

    @Bean
    public CandelGenerator heureCandelGenerator() {
        return new CandelGenerator(60);
    }
}
