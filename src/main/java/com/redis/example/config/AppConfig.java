package com.redis.example.config;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.redis.example.model.Books;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {

    @Value("${redis.hostname:localhost}")
    private String redisHostname;

    @Value("${redis.port:6379}")
    private Integer port;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        System.out.println("redisHostname====" + redisHostname);
        System.out.println("port====" + port);


//        final RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration("localhost", 6379);
        final RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(redisHostname, port);
        return new JedisConnectionFactory(standaloneConfig);
    }


    /**
     * Jedis
     */
    /* If you're working with HA Redis CLuster and want to have the support to read the data from slave in case master goes down
    so, use below connection factory example with sentinel.
    Sentinel is one of the functionality provided by Redis where if any of the nodes goes down, still application won't face the issue.
    */

    /*@Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new JedisConnectionFactory(sentinelConfig);
    }*/
    @Bean
    public RedisTemplate<Integer, Books> redisTemplate() {
        final RedisTemplate<Integer, Books> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        redisTemplate.setStringSerializer(new StringRedisSerializer());
//        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
