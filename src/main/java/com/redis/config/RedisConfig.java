package com.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${azure.redis.url}")
    private String url;
    @Value("${azure.redis.pwd}")
    private String pwd;
    @Value("${azure.redis.map}")
    private String map;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec())
                .useSingleServer()
                .setAddress(url)
                .setPassword(pwd);
        return Redisson.create(config);
    }

    @Bean
    public CacheManager cacheManager() {
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setTTL(60);
        Map<String, CacheConfig> config = new HashMap<>();
        config.put("countries", cacheConfig);
        return new RedissonSpringCacheManager(redissonClient());
    }

}
