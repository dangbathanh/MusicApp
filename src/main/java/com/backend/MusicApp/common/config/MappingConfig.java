package com.backend.MusicApp.common.config;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MappingConfig {
    @Bean @Primary
    public ObjectMapper commonObjectMapper() {
        return new ObjectMapper()
                .registerModules(new JavaTimeModule(), new ParameterNamesModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean("redisObjectMapper")
    public ObjectMapper redisObjectMapper() {
        return new ObjectMapper()
                .registerModules(new JavaTimeModule(), new ParameterNamesModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                        ObjectMapper.DefaultTyping.EVERYTHING, JsonTypeInfo.As.PROPERTY);
    }
}
