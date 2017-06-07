package com.tistory.heowc.config;

import com.tistory.heowc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired JedisConnectionFactory jedisConnectionFactory;

    @Bean("userRedisTemplate")
    public RedisTemplate<String, User> userRedisTemplate() {
        RedisTemplate<String, User> template =  new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        return template;
    }
}