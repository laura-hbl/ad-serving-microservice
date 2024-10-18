package com.arago.adserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * Configuration for Redis connection using Jedis.
 * This class provides the necessary beans for connecting to a Redis server.
 */
@Configuration
public class RedisConfig {

    /**
     * Configures and provides a {@link JedisConnectionFactory} bean.
     * Sets up the connection to the Redis server with host, port, and timeout settings.
     *
     * @return a configured {@link JedisConnectionFactory} instance
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(10));
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    }

    /**
     * Configures and provides a {@link RedisTemplate} bean.
     * Associates the RedisTemplate with the provided {@link JedisConnectionFactory}.
     *
     * @param jedisConnectionFactory the {@link JedisConnectionFactory} to use
     * @return a configured {@link RedisTemplate} instance
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
}
