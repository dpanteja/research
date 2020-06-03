package com.dandi.api;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.dandi.api.serilizers.RowSetModule;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RowSetConfig {
    @Bean
    public ObjectMapper jsonObjectMapper() {
        ArrayList<Module> modules = new ArrayList<>();

        modules.add(new RowSetModule());

        return Jackson2ObjectMapperBuilder.json()
                .modules(modules)
                .build();
    }
}
