package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * В этом примере Spring Boot автоматически сопоставит значения app.name и
 * app.description из файла конфигурации с полями name и description в классе
 * AppProperties.
 */

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String name;
    private String description;

    // getters
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
