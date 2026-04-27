package com.backend.MusicApp.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisConfig {
    @Qualifier("redisObjectMapper")
    private final ObjectMapper redisObjectMapper;

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        var serializer = new GenericJackson2JsonRedisSerializer(redisObjectMapper);

        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(24))
                .disableCachingNullValues()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                );
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration())
                .withCacheConfiguration("home_catalog",
                        cacheConfiguration().entryTtl(Duration.ofMinutes(30)))
                .withCacheConfiguration("song_metadata",
                        cacheConfiguration().entryTtl(Duration.ofDays(7)))
                .withCacheConfiguration("album_detail",
                        cacheConfiguration().entryTtl(Duration.ofHours(12)))
                .build();
    }
}