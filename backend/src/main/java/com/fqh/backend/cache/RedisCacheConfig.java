package com.fqh.backend.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisCacheConfig {
    // 配置序列化
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 设置 Key 的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置 Value 的序列化方式
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置 Hash Key 的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置 Hash Value 的序列化方式
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    // 配置缓存key前缀, 过期时间
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .prefixCacheNameWith("myapp::")
                .entryTtl(Duration.ofMinutes(60));
    }


    // 缓存管理器
    @Bean(name = "myCacheManage")
    public CacheManager myCacheManage(RedisConnectionFactory factory) {
        var configuration = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(600L))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(factory)
                .cacheDefaults(configuration)
                .build();
    }
}
