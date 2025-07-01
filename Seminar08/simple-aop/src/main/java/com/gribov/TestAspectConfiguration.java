package com.gribov;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAspectConfiguration {
    @Bean
    public TestAspect testAspect(){
        return new TestAspect();
    }
}
