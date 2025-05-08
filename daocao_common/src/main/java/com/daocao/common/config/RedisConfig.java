package com.daocao.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Project: daocao
 * @Description: Redis配置类
 * @Author: bf
 * @Date: 2025/3/25 09:41:33
 * @Version: 1.0
 */
@Configuration
// 开启缓存注解
@EnableCaching
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(redisConnectionFactory);
        FastJson2JsonRedisSerializer<Object> objectFastJson2JsonRedisSerializer =
                new FastJson2JsonRedisSerializer<>(Object.class);
        // 使用 StringRedisSerializer 来序列化和反序列化 redis 的 key 值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        // 使用 FastJson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值
        template.setValueSerializer(objectFastJson2JsonRedisSerializer);
        template.setHashValueSerializer(objectFastJson2JsonRedisSerializer);

        template.afterPropertiesSet(); // 构造一个新的RedisTemplate实例。
        return template;
    }
}
