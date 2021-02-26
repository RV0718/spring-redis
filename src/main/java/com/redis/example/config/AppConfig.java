package com.redis.example.config;

import com.redis.example.model.Books;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfig {

    //    @Value("${redis.hostname:localhost}")
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.jedis.pool.max-total}")
    private int maxTotal;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.password}")
    private String redisPassword;


    @Bean
    public JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        // maximum number of free connections, default 8
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        // Maximum number of connections Default 16
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        // Get the maximum number of milliseconds to wait for the connection (BlockWhenExhausted if set to block), throw an exception if it times out, less than zero: block the indeterminate time,
        // default -1
        genericObjectPoolConfig.setMinIdle(minIdle);
        return jedisPoolingClientConfigurationBuilder.poolConfig(genericObjectPoolConfig).build();
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        final RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(redisHost, port);
        if(StringUtils.isNotEmpty(redisPassword)){
            standaloneConfig.setPassword(RedisPassword.of(redisPassword));
        }
        return new JedisConnectionFactory(standaloneConfig,getJedisClientConfiguration());
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
    public RedisTemplate<Integer, Object> redisTemplate() {
        final RedisTemplate<Integer, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}
